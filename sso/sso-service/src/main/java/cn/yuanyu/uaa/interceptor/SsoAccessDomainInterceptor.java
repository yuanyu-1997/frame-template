package cn.yuanyu.uaa.interceptor;

import cn.yuanyu.uaa.enums.ErrorCodeEnum;
import cn.yuanyu.uaa.mapper.SsoClientDetailsMapper;
import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import cn.yuanyu.uaa.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于校验请求获取Token的回调URL是否在白名单中
 */
@Component
public class SsoAccessDomainInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SsoClientDetailsMapper ssoClientDetailsMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String redirectUri = request.getParameter("redirect_uri");
        if (StringUtils.isNoneBlank(redirectUri)) {
            // 查询数据库中的回调地址的白名单
            SsoClientDetails ssoClientDetails = ssoClientDetailsMapper.selectByRedirectUrl(redirectUri);
            if (ssoClientDetails != null) {
                return true;
            } else {
                // 如果回调URL不在白名单中，则返回错误提示
                return generateErrorResponse(response, ErrorCodeEnum.INVALID_REDIRECT_URI);
            }
        } else {
            return generateErrorResponse(response, ErrorCodeEnum.INVALID_REQUEST);
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
