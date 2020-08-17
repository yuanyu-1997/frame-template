package cn.yuanyu.ssoclient;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SsoClientApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(SsoClientApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}
