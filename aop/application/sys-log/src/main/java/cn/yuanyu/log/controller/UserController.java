package cn.yuanyu.log.controller;

import cn.yuanyu.log.annotation.SysLog;
import cn.yuanyu.log.entity.UserEntity;
import cn.yuanyu.log.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     */
    @SysLog(value = "根据用户主键查询用户")
    @GetMapping("/{id}")
    public UserEntity queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    /**
     *
     */
    @SysLog(value = "查询所有用户")
    @GetMapping
    public List<UserEntity> queryAll() {
        return userService.allUser();
    }
}