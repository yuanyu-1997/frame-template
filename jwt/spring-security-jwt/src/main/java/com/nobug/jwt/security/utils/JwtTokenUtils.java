package com.nobug.jwt.security.utils;


import com.nobug.jwt.security.constants.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

/**
 * JWT工具类
 */
public class JwtTokenUtils {

    /**
     * 生成足够的安全随机密钥，以适合符合规范的签名
     */
    private static final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JwtConfig.JWT_SECRET_KEY);

    private static final SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

    public static String createToken(String username, List<String> roles, boolean isRememberMe) {
        long expiration = isRememberMe ? JwtConfig.EXPIRATION_REMEMBER : JwtConfig.EXPIRATION;
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expiration);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuedAt(createdDate)
                .setSubject(username)
                .setExpiration(expirationDate)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expiredDate = getTokenBody(token).getExpiration();
        return expiredDate.before(new Date());
    }

    public static String getUsernameByToken(String token) {
        return getTokenBody(token).getSubject();
    }


    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
