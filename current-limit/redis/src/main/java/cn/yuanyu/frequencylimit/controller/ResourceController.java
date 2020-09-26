package cn.yuanyu.frequencylimit.controller;

import cn.yuanyu.frequencylimit.annotation.RequestLimit;
import cn.yuanyu.frequencylimit.service.impl.CurrentLimitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResourceController {

    @Autowired
    private CurrentLimitServiceImpl currentLimitService;

    // http://localhost:5000/heihei/protectResource
    @RequestLimit(count = 10) // 一分钟能够访问10次
    @GetMapping("/protectResource")
    public Object protectResource(HttpServletRequest req) {
        return "保护资源";
    }

    // http://localhost:5000/heihei/simpleResource
    @GetMapping("/simpleResource")
    public Object simpleResource(HttpServletRequest req) {
        return "普通资源";
    }

}
