package cn.yuanyu.ssm.controller;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HelloWorldControllerTest {

    public static void main(String[] args) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:2000/ssm/map");
        CloseableHttpResponse response = null;
        response = httpClient.execute(httpGet);
        System.out.println("code => " + response.getStatusLine().getStatusCode());
        // 4. 解析响应
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity());
            System.out.println(content);
        }
    }
}