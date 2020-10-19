package cn.yuanyu.app.controller.sys;

import cn.yuanyu.app.common.utils.R;
import cn.yuanyu.app.modules.sys.form.SysLoginForm;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;


public class SysLoginControllerTest {
    private final RestTemplate restTemplate = new RestTemplate();
    @Test
    public void login() {
        String url = "http://localhost:6969/sys/login";
        SysLoginForm sysLoginForm = new SysLoginForm();
        sysLoginForm.setUsername("张三");
        sysLoginForm.setPassword("u0h5xlqUh44ccogtzsP0t7L1yAe2mi3OxmtDyLyvZ0c=");

        R res = restTemplate.postForObject(url, sysLoginForm, R.class);
        System.out.println("res => " + res);

    }
}