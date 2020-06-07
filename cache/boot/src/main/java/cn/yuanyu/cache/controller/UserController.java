package cn.yuanyu.cache.controller;

import cn.yuanyu.cache.entity.User;
import cn.yuanyu.cache.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
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
     * http://localhost:6969/user/2
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public User selectById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }


    /**
     * http://localhost:6969/user/2
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

    /**
     * http://localhost:6969/user
     * ["6","7","8"]
     * 通过主键数组批量删除数据
     */
    @DeleteMapping
    public void delete(@RequestBody Integer[] ids) {
        userService.delete(ids);
    }
}