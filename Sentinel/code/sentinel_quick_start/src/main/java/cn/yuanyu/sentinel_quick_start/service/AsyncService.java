package cn.yuanyu.sentinel_quick_start.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yuanyu
 */
@Service
public class AsyncService {

    // @Async表示方法是异步调用方法
    @Async
    public void hello(){
        System.out.println("异步开始-----");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步结束-----");
    }

}
