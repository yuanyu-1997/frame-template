package cn.yuanyu.controller;

import cn.yuanyu.entity.User;
import cn.yuanyu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * http://localhost:6969/user/1
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    /**
     * http://localhost:6969/user
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * http://localhost:6969/user
     */
    @GetMapping
    public List<User> finAll() {
        return userService.findAll();
    }

}