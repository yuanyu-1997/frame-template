package cn.yuanyu.oauthclient;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class OauthClientApplication {
	public static void main(String[] args) {
        new SpringApplicationBuilder(OauthClientApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
	}
}
