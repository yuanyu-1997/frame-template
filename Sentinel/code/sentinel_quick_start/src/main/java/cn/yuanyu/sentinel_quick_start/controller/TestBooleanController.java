package cn.yuanyu.sentinel_quick_start.controller;

import com.alibaba.csp.sentinel.SphO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 返回布尔值方式定义资源
 *
 * @author yuanyu
 */
@RestController
public class TestBooleanController {

    // http://localhost:6969/boolean
    @GetMapping("boolean")
    public boolean hello() { // 返回值为 boolean 值
        //使用限流规则
        if (SphO.entry("Sentinel_Boolean")) {      //限流入口
            try {
                System.out.println("Hello Sentinel!"); //被保护的资源
                return true;
            } finally {
                SphO.exit();                           //限流的出口
            }
        } else {
            System.out.println("系统繁忙，请稍候");      //被限流或降级的处理
            return false;
        }

    }


}
