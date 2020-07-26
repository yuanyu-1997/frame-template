

package io.renren;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


/**
 * @author yuanyu
 */
@SpringBootApplication
public class RenRenApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RenRenApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}