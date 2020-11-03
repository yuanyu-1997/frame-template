package cn.yuanyu.app.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author yuanyu
 */
@Component
public class GatewayConfiguration {

    //初始化限流或者降级的回调函数
    @PostConstruct
    public void doInit(){
        //设置限流或者降级的回调函数
        GatewayCallbackManager.setBlockHandler(new BlockRequestHandler() {
            //被限流或降级处理的方法
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                return ServerResponse.status(200).syncBody("系统繁忙，请稍候");
            }
        });
    }

}
