package cn.yuanyu.cache.controller;

import cn.yuanyu.cache.entity.User;
import cn.yuanyu.cache.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 通过主键查询单条数据
     */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }


}