package cn.yuanyu.uaa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


// https://gitee.com/wsjlb/OAuth2.0Demo
@SpringBootApplication
public class UaaApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(UaaApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}
