package cn.yuanyu.sentinel_quick_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * java -Dserver.port=9000 -jar sentinel-dashboard-1.7.2.jar
 *
 * -Dcsp.sentinel.dashboard.server=localhost:9000 -Dproject.name=SentinelQuickStart
 *
 * @author yuanyu
 */
@SpringBootApplication
@EnableAsync  //开启异步调用的支持
public class SentinelQuickStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelQuickStartApplication.class, args);
    }


    //INFO: Sentinel log output type is: file
    //INFO: Sentinel log charset is: utf-8
    //INFO: Sentinel log base directory is: C:\Users\yuanyu\logs\csp\   // 日志目录
    //INFO: Sentinel log name use pid is: false
}
