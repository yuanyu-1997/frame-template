package com.nobug.jwt;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author yuanyu
 */
@EnableJpaAuditing
@SpringBootApplication
public class JWTServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JWTServer.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
