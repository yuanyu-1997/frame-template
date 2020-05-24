package cn.yuanyu.api;

import cn.yuanyu.service.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Test
    public void registerOk() {
        String url = "http://localhost:6969/user";
        UserModel userModel = new UserModel("蔡徐坤", "123456", "坤坤", 30, "kunkun@vip.com");
        new RestTemplate().postForLocation(url, userModel);
    }

    @Test
    public void registerError() {
        String url = "http://localhost:6969/user";
        UserModel userModel = new UserModel();
        userModel.setAge(110);
        new RestTemplate().postForLocation(url, userModel);
    }
}