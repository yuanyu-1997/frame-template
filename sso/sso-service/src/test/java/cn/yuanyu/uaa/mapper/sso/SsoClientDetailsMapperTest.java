package cn.yuanyu.uaa.mapper.sso;

import cn.yuanyu.uaa.mapper.SsoClientDetailsMapper;
import cn.yuanyu.uaa.model.sso.SsoClientDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SsoClientDetailsMapperTest {

    @Autowired
    private SsoClientDetailsMapper ssoClientDetailsMapper;

    // TODO 2.添加client信息
    @Test
    public void init(){
        SsoClientDetails ssoClientDetails = new SsoClientDetails("user-center", "用户中心模块", "http://127.0.0.1:5000/user-center/login", "http://127.0.0.1:5000/user-center/logout", 1);
        ssoClientDetailsMapper.insert(ssoClientDetails);

    }

}