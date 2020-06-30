package cn.yuanyu.guavatoken.interceptor;

import cn.yuanyu.guavatoken.enums.ResponseEnum;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanyu
 */
@Slf4j
@Component("rateLimitInterceptor")
public class RateLimitInterceptor extends AbstractInterceptor {

    /**
     * 单机全局限流器 (限制QPS为)
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(2);

    public static void setRate(double limiterQPS) {
        rateLimiter.setRate(limiterQPS);
    }

    @Override
    protected ResponseEnum preFilter(HttpServletRequest request) {
        if (!rateLimiter.tryAcquire()) {
            log.warn("限流中......");
            return ResponseEnum.RATE_LIMIT;
        }
        return ResponseEnum.SUCCESS;
    }
}