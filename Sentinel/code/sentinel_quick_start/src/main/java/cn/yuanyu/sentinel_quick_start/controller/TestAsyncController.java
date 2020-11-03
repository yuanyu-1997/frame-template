package cn.yuanyu.sentinel_quick_start.controller;

import cn.yuanyu.sentinel_quick_start.service.AsyncService;
import com.alibaba.csp.sentinel.AsyncEntry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步调用支持
 *
 * @author yuanyu
 */
@RestController
public class TestAsyncController {

    @Autowired
    private AsyncService asyncService;

    // http://localhost:6969/async
    @GetMapping("async")
    public void hello() {
        //使用限流规则
        AsyncEntry asyncEntry = null;
        try {
            asyncEntry = SphU.asyncEntry("Sentinel_Async"); //定义资源，限流的入口
            asyncService.hello();                                 //调用异步的方法，被保护的资源
        } catch (BlockException e) {
            System.out.println("系统繁忙，请稍候");                 //被限流或降级的处理
        } finally {
            if (asyncEntry != null) {
                asyncEntry.exit();                                //限流的出口
            }
        }

    }


}
