package com.nobug.jwt.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nobug.jwt.security.constants.JwtConfig;
import com.nobug.jwt.security.dto.LoginRequest;
import com.nobug.jwt.security.utils.JwtTokenUtils;
import com.nobug.jwt.system.util.R;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 如果用户名和密码正确，那么过滤器将创建一个JWT Token 并在HTTP Response 的header中返回它，格式：token: "Bearer +具体token值"
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();

    private final AuthenticationManager authenticationManager;

    /**
     * 设置URL，以确定是否需要身份验证
     */
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl(JwtConfig.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // 获取登录的信息
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            rememberMe.set(loginRequest.getRememberMe());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 如果验证成功，就生成token并返回
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDetails jwtUser = (UserDetails) authentication.getPrincipal();
        // 创建 Token
        String token = JwtTokenUtils.createToken(jwtUser.getUsername(), rememberMe.get());
        rememberMe.remove();

        String res = objectMapper.writeValueAsString(R.ok().put(JwtConfig.HEADER, token));
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(res);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    }
}
