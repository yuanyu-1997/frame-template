package cn.yuanyu.multids.api;

import cn.yuanyu.multids.entity.User;
import cn.yuanyu.multids.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public User insert(@RequestBody User user) {
        return userService.insert(user);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public User selectOne(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping
    public List<User> allUser() {
        return userService.allUser();
    }

}