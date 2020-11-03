package cn.yuanyu.app.client.agent;

import cn.yuanyu.app.client.service.FallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yuanyu
 */
@FeignClient(value = "sentinel-feign-provider", fallback = FallbackService.class)
public interface FeignAgent {

    @GetMapping("/hello")
    String hello();

}
