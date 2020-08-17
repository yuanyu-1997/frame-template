package cn.yuanyu.uaa.mapper;

import cn.yuanyu.uaa.model.AuthScope;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthScopeMapperTest {

    @Autowired
    private AuthScopeMapper authScopeMapper;


    @Test
    public void insert(){
        authScopeMapper.insert(new AuthScope("super", ""));
        authScopeMapper.insert(new AuthScope("basic", ""));

    }
}