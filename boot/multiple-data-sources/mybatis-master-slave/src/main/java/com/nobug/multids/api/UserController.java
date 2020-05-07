package com.nobug.multids.api;

import com.nobug.multids.entity.User;
import com.nobug.multids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * http://localhost:6969/user
     * 插入一条用户记录
     */
    @PostMapping("")
    public User insert(@RequestBody User user) {
        return userService.insert(user);
    }


    /**
     * http://localhost:6969/user/284950495
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable("id") Integer id) {
        return userService.queryById(id);
    }

}
