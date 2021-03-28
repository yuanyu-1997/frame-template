package cn.baker.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * @author yuanyu
 */
@SpringBootApplication
public class APP {
    public static void main(String[] args) {
        new SpringApplicationBuilder(APP.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
