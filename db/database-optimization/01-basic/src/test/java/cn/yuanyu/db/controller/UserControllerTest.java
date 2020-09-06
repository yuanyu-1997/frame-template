package cn.yuanyu.db.controller;


import cn.yuanyu.db.pojo.User;
import cn.yuanyu.db.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class UserControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public void insert_user() {
        User user = new User();
        String url = "http://localhost:5000/user";
        Result res = new RestTemplate().postForObject(url, user, Result.class);
        log.info("res => {}", res);
    }
    
    @Test
    public void test_insert_user(){
        for (int i = 0; i < 100; i++) {
            insert_user();
        }
    }

}