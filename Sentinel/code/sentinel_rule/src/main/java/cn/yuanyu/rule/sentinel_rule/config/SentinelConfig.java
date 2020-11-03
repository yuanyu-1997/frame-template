package cn.yuanyu.rule.sentinel_rule.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanyu
 */
@Component
public class SentinelConfig {

    @PostConstruct
    public void doInit() {
        //获取请求来源的ip地址
        WebCallbackManager.setRequestOriginParser(new RequestOriginParser() {
            @Override
            public String parseOrigin(HttpServletRequest httpServletRequest) {
                return httpServletRequest.getRemoteAddr();
            }
        });
    }

}
