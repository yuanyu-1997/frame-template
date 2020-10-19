package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.form.RegisterForm;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class AppControllerTest {
    private final RestTemplate restTemplate = new RestTemplate();

    // 注册
    @Test
    public void register() {
        String url = "http://localhost:6969/app/register";
        RegisterForm registerForm = new RegisterForm();
        registerForm.setMobile("17783649163");
        registerForm.setPassword("123");
        R res = restTemplate.postForObject(url, registerForm, R.class);
        System.out.println("res => " + res);
        login(registerForm.getMobile(), registerForm.getPassword());
    }

    // 登陆
    public void login(String mobile, String password) {
        String url = "http://localhost:6969/app/login";
        LoginForm loginForm = new LoginForm();
        loginForm.setMobile(mobile);
        loginForm.setPassword(password);
        R res = restTemplate.postForObject(url, loginForm, R.class);
        System.out.println("res => " + res);
        //
        String token = (String) res.get("token");
        userInfo(token);
    }

    // 获取用户信息
    public void userInfo(String token) {
        String url = "http://localhost:6969/app/userInfo?token=" + token;
        R res = restTemplate.getForObject(url, R.class);
        System.out.println("res => " + res);
        userId(token);
    }

    // 获取用户ID
    public void userId(String token) {
        String url = "http://localhost:6969/app/userId?token=" + token;
        R res = restTemplate.getForObject(url, R.class);
        System.out.println("res => " + res);
        notToken();
    }

    // 无须token测试
    public void notToken() {
        String url = "http://localhost:6969/app/notToken";
        R res = restTemplate.getForObject(url, R.class);
        System.out.println("res => " + res);
    }


}