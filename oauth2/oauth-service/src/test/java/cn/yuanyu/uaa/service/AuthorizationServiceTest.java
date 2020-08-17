package cn.yuanyu.uaa.service;

import cn.yuanyu.uaa.model.AuthClientDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthorizationServiceTest {


    @Autowired
    private AuthorizationService authorizationService;

    // TODO 1.客户端注册
    @Test
    public void register(){
        AuthClientDetails authClientDetails = new AuthClientDetails();
        authClientDetails.setClientName("xxx客户端");
        authClientDetails.setRedirectUri("http://127.0.0.1:2500/xxx-client/login");
        authClientDetails.setDescription("这是xxx客户端服务");
        authorizationService.register(authClientDetails);
    }

}