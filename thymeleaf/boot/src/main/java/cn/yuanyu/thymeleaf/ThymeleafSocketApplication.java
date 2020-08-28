package cn.yuanyu.thymeleaf;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
// https://www.cnblogs.com/jiangbei/p/8462294.html
// https://www.cnblogs.com/msi-chen/p/10974009.html
@SpringBootApplication
public class ThymeleafSocketApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ThymeleafSocketApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}
