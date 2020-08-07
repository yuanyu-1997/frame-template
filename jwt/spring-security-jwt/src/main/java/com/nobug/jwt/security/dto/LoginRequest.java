package com.nobug.jwt.security.dto;

import lombok.Data;


/**
 * 用户登录请求DTO
 */
@Data
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 记住我
     */
    private Boolean rememberMe;
}
