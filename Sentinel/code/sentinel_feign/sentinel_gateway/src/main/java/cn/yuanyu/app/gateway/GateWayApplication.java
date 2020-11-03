package cn.yuanyu.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yuanyu
 */
// http://localhost:9013/hello

@SpringBootApplication
@EnableDiscoveryClient //开启Eureka客户端发现功能
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
