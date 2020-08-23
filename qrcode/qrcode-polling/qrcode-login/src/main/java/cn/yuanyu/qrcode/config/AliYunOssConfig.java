package cn.yuanyu.qrcode.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliYunOssConfig {
    @Value("${ali.oss.endpoint}")
    private String endpoint;
    @Value("${ali.oss.accessKeyId}")
    private String accessKeyId;
    @Value("${ali.oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${ali.oss.bucketName}")
    private String bucketName;
    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder()
                .build(endpoint, accessKeyId, accessKeySecret);
    }
}
