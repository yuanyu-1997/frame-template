package com.nobug.jwt.security.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * jwt相关配置常量
 */
@Configuration
@Order(Integer.MIN_VALUE) // 先执行
public class JwtConfig {
    /**
     * 登录的地址
     */
    public static String AUTH_LOGIN_URL;
    /**
     * rememberMe为false的时候过期时间是1个小时
     */
    public static Long EXPIRATION;
    /**
     * rememberMe为true的时候过期时间是7天
     */
    public static Long EXPIRATION_REMEMBER;
    /**
     * JWT签名密钥
     */
    public static String JWT_SECRET_KEY;

    public static String HEADER;


    // 方法
    @Value("${yuanyu.jwt.auth_login_url}")
    public void setAuthLoginUrl(String authLoginUrl) {
        AUTH_LOGIN_URL = authLoginUrl;
    }

    @Value("${yuanyu.jwt.expiration}")
    public void setEXPIRATION(Long expiration) {
        EXPIRATION = expiration;
    }

    @Value("${yuanyu.jwt.expiration_remember}")
    public void setExpirationRemember(Long expirationRemember) {
        EXPIRATION_REMEMBER = expirationRemember;
    }

    @Value("${yuanyu.jwt.jwt_secret_key}")
    public void setJwtSecretKey(String jwtSecretKey) {
        JWT_SECRET_KEY = jwtSecretKey;
    }

    @Value("${yuanyu.jwt.header}")
    public void setHEADER(String header) {
        JwtConfig.HEADER = header;
    }
}
