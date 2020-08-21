package cn.yuanyu.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * https://blog.csdn.net/HOMERUNIT/article/details/80861096
 * https://www.jb51.net/article/172240.htm
 * https://blog.csdn.net/qq_35387940/article/details/93483678
 * https://www.jianshu.com/p/aea406f24b77
 */
@Slf4j
@SpringBootApplication
public class WebSocketApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebSocketApplication.class)
				.web(WebApplicationType.SERVLET)
				.run(args);
	}
}
