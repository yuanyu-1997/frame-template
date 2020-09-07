package cn.yuanyu.guava.controller;


import cn.yuanyu.guava.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * guava实现布隆过滤器
 */
@RestController
@RequestMapping("bloom")
public class BloomFilterController {

    @Autowired
    private RedisService redisService;

    /**
     * redis布隆过滤器添加
     */
    // http://localhost:4000/bloom/redisAdd?name=zhangsan&id=66
    @GetMapping("redisAdd")
    public boolean redisAdd(@RequestParam String name, @RequestParam Integer id) {
        return redisService.bloomFilterAdd(name, id);
    }

    /**
     * redis布隆过滤器判断是否存在
     */
    // http://localhost:4000/bloom/redisIdExists?name=zhangsan&id=66
    @GetMapping("redisIdExists")
    public boolean redisIdExists(@RequestParam String name, @RequestParam Integer id) {
        return redisService.bloomFilterExists(name, id);
    }

}
