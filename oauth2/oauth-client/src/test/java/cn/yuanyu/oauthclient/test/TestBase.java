package cn.yuanyu.oauthclient.test;

import cn.yuanyu.oauthclient.model.AuthorizationResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

/**
 * 测试RestTemplate访问OAuth2服务端
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestBase {

    @Value("${own.oauth2.client-id}")
    private String clientId;

    @Value("${own.oauth2.scope}")
    private String scope;

    @Value("${own.oauth2.client-secret}")
    private String clientSecret;

    @Value("${own.oauth2.user-authorization-uri}")
    private String authorizationUri;

    @Value("${own.oauth2.access-token-uri}")
    private String accessTokenUri;

    @Value("${own.oauth2.resource.userInfoUri}")
    private String userInfoUri;

    @Test
    public void testSelect() {
        AuthorizationResponse response = new RestTemplate().getForObject(accessTokenUri, AuthorizationResponse.class
                , clientId, clientSecret, "09139f411ca8cdee4c4dfac621a0c59463ecf418", "http://localhost:7080/login");

        System.out.println(response);
    }

}
