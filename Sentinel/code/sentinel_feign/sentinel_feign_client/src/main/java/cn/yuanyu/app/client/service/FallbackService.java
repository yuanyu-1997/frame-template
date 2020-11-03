package cn.yuanyu.app.client.service;

import cn.yuanyu.app.client.agent.FeignAgent;
import org.springframework.stereotype.Component;

/**
 * @author yuanyu
 */
//限流或者降级的回调类
@Component
public class FallbackService implements FeignAgent {
    //限流和降级的处理
    @Override
    public String hello() {
        return "系统繁忙，请稍候";
    }
}
