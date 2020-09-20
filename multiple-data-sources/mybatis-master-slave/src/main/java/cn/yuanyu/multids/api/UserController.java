package cn.yuanyu.multids.api;

import cn.yuanyu.multids.entity.User;
import cn.yuanyu.multids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * http://localhost:6969/user
 *
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 用户注册
     */
    @PostMapping("")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Integer id) {
        return userService.queryById(id);
    }


}
