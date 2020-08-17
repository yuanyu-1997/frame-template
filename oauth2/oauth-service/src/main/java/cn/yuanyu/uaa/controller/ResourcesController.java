package cn.yuanyu.uaa.controller;

import cn.yuanyu.uaa.enums.ErrorCodeEnum;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.model.AuthAccessToken;
import cn.yuanyu.uaa.service.AuthorizationService;
import cn.yuanyu.uaa.service.UserService;
import cn.yuanyu.uaa.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过 Access Token 访问的API服务
 */
@RestController
@RequestMapping("/api")
public class ResourcesController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    // http://127.0.0.1:8000/uaa/api/users/getInfo
    @RequestMapping(value = "/users/getInfo")
    public String getUserInfo(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        //查询数据库中的 Access Token
        AuthAccessToken authAccessToken = authorizationService.selectByAccessToken(accessToken);
        if (authAccessToken != null) {
            User user = userService.selectUserInfoByScope(authAccessToken.getUserId(), authAccessToken.getScope());
            return JsonUtils.toJson(user);
        } else {
            return this.generateErrorResponse(ErrorCodeEnum.INVALID_GRANT);
        }
    }

    /**
     * 组装错误请求的返回
     */
    private String generateErrorResponse(ErrorCodeEnum errorCodeEnum) {
        Map<String, Object> result = new HashMap<>(2);
        result.put("error", errorCodeEnum.getError());
        result.put("error_description", errorCodeEnum.getErrorDescription());

        return JsonUtils.toJson(result);
    }

}
