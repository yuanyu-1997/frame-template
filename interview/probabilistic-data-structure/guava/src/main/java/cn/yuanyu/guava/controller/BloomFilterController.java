package cn.yuanyu.guava.controller;


import cn.yuanyu.guava.service.BloomFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * guava实现布隆过滤器
 */
@RestController
@RequestMapping("bloom")
public class BloomFilterController {

    @Autowired
    private BloomFilterService bloomFilterService;

    // http://localhost:4000/bloom/idExists/1
    @GetMapping("idExists/{id}")
    public boolean ifExists(@PathVariable Integer id) {
        return bloomFilterService.userIdExists(id);
    }
}
