package cn.yuanyu.controller;

import cn.yuanyu.entity.User;
import cn.yuanyu.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * http://localhost:6969/user
 *
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }


    @GetMapping
    public List<User> queryAll() {
        return userService.allUser();
    }


}