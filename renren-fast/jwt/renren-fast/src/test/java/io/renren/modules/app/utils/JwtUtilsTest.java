package io.renren.modules.app.utils;


import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtilsTest {
    // 配置文件获取
    private static final Integer timeLimit = (int) TimeUnit.DAYS.toMillis(1); // 过期时间
    private static final String secretKey = "MJIDHLGDNGLHDLDHFLKHFD&*FD……*F……"; // 密钥（防篡改的关键，不能泄漏）
    /**
     * 生成token
     *
     * @param id（payload） 唯一标识用户的（用户名或者用户组件），方便后面认证的时候取出来
     * @return jwt
     */
    public static String createToken(String id, String subject) {
        byte[] bytes = Base64.encodeBase64(secretKey.getBytes(StandardCharsets.UTF_8));
        // 签发时间
        Date nowDate = new Date();
        // 过期时间
        Date expireDate = new Date(nowDate.getTime() + timeLimit);
        // 开始生效时间
        Date notBeforeDate = new Date(nowDate.getTime() + (int) TimeUnit.MINUTES.toMillis(1));
        return Jwts.builder()
                // Header
                .setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS512, bytes)
                // PayLoad
                .setIssuer("")                 // 签发者
                .setSubject(subject)           // 面向主体
                .setAudience("")               // 接收方
                .setExpiration(expireDate)      // 过期时间
                .setNotBefore(notBeforeDate)    // 开始生效时间
                .setIssuedAt(nowDate)           // 签发时间
                .setId(id)                      //  唯一标识
                .compact();
    }
    /**
     * 验证token
     *
     * @param jwt jwt
     * @return Claims
     */
    public static Claims parseJWT(String jwt) {
        byte[] bytes = Base64.encodeBase64(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(bytes)
                .parseClaimsJws(jwt)
                .getBody();
    }
    @Test
    public void test_() throws Exception {
        String token = createToken("87451234678", "{}");
        System.out.println(token);
        TimeUnit.MINUTES.sleep(1);
        Claims claims = parseJWT(token); // 如果当前token还没到生效时间，会抛出异常

        System.out.println(claims);
        System.out.println("iss: " + claims.getIssuer());                   // iss
        System.out.println("sub: " + claims.getSubject());                  // sub
        System.out.println("aud: " + claims.getAudience());                 // aud
        System.out.println("exp: " + formatData(claims.getExpiration()));   // exp
        System.out.println("nbf: " + formatData(claims.getNotBefore()));    // nbf
        System.out.println("iat: " + formatData(claims.getIssuedAt()));     // iat
        System.out.println("jti: " + claims.getId());                       // jti
    }

    public static JwsHeader header(String jwt){
        byte[] bytes = Base64.encodeBase64(secretKey.getBytes(StandardCharsets.UTF_8));
        return  Jwts.parser()
                .setSigningKey(bytes)
                .parseClaimsJws(jwt)
                .getHeader();
    }

    @Test
    public void test_header() throws Exception {
        String token = createToken("87451234678","{}");
        System.out.println(token);
        TimeUnit.MINUTES.sleep(1);
        Header header = header(token);
        System.out.println(header.getType());
        System.out.println(header.getContentType());
        System.out.println(header.getCompressionAlgorithm());
        System.out.println(header);
    }

    private static String formatData(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
    // https://stormpath.com/blog/jwt-java-create-verify







}