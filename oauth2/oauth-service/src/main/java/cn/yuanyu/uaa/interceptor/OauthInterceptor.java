package cn.yuanyu.uaa.interceptor;

import cn.yuanyu.uaa.common.Constants;
import cn.yuanyu.uaa.common.SpringContextUtils;
import cn.yuanyu.uaa.enums.ErrorCodeEnum;
import cn.yuanyu.uaa.mapper.AuthClientDetailsMapper;
import cn.yuanyu.uaa.mapper.AuthClientUserMapper;
import cn.yuanyu.uaa.mapper.AuthScopeMapper;
import cn.yuanyu.uaa.model.AuthClientDetails;
import cn.yuanyu.uaa.model.AuthClientUser;
import cn.yuanyu.uaa.model.AuthScope;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 检查是否已经存在授权
 */
@Slf4j
@Component
public class OauthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private AuthClientUserMapper authClientUserMapper;

    @Autowired
    private AuthClientDetailsMapper authClientDetailsMapper;

    @Autowired
    private AuthScopeMapper authScopeMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 参数信息
        String params = "?redirect_uri=" + SpringContextUtils.getRequestUrl(request);
        // 客户端ID
        String clientIdStr = request.getParameter("client_id");
        // 权限范围
        String scopeStr = request.getParameter("scope");
        // 回调URL
        String redirectUri = request.getParameter("redirect_uri");
        // 返回形式
        String responseType = request.getParameter("response_type");
        // 获取session中存储的token
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (StringUtils.isNoneBlank(clientIdStr) && StringUtils.isNoneBlank(scopeStr) && StringUtils.isNoneBlank(redirectUri) && "code".equals(responseType)) {
            params = params + "&client_id=" + clientIdStr + "&scope=" + scopeStr;
            // 1.查询是否存在授权信息
            AuthClientDetails clientDetails = authClientDetailsMapper.selectByClientId(clientIdStr);
            AuthScope scope = authScopeMapper.selectByScopeName(scopeStr);
            if (clientDetails == null) {
                return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_CLIENT);
            }
            if (scope == null) {
                return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_SCOPE);
            }
            if (!clientDetails.getRedirectUri().equals(redirectUri)) {
                return this.generateErrorResponse(response, ErrorCodeEnum.REDIRECT_URI_MISMATCH);
            }
            // 2.查询用户给接入的客户端是否已经授权
            AuthClientUser clientUser = authClientUserMapper.selectByClientId(clientDetails.getId(), user.getId(), scope.getId());
            if (clientUser != null) {
                return true;
            } else {
                //如果没有授权，则跳转到授权页面
                String redirectUrl = request.getContextPath() + "/oauth2/authorizePage" + params;
                response.sendRedirect(redirectUrl);
                log.info("跳转地址 => {}", redirectUrl);
                return false;
            }
        } else {
            return this.generateErrorResponse(response, ErrorCodeEnum.INVALID_REQUEST);
        }
    }

    /**
     * 组装错误请求的返回
     */
    private boolean generateErrorResponse(HttpServletResponse response, ErrorCodeEnum errorCodeEnum) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        Map<String, String> result = new HashMap<>(2);
        result.put("error", errorCodeEnum.getError());
        result.put("error_description", errorCodeEnum.getErrorDescription());

        response.getWriter().write(JsonUtils.toJson(result));
        return false;
    }

}
