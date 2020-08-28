package cn.yuanyu.websocket;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ThymeleafSocketApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ThymeleafSocketApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}
