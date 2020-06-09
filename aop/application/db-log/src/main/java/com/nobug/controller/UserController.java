package com.nobug.controller;


import com.nobug.aop.OperateLog;
import com.nobug.entity.Item;
import com.nobug.entity.User;
import com.nobug.service.UserService;
import com.nobug.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yuanyu
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @OperateLog
    @PostMapping("")
    public Result insert(@RequestBody User user){
        try {
            userService.insert(user);
            return new Result(true,"操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false,"操作失败");
        }
    }

    @OperateLog
    @PutMapping("")
    public Result update(@RequestBody User user){
        try {
            userService.update(user);
            return new Result(true,"操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false,"操作失败");
        }
    }

    @OperateLog
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id")Integer id){
        try {
            userService.delete(id);
            return new Result(true,"操作成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Result(false,"操作失败");
        }
    }


    @OperateLog
    @GetMapping("/{id}")
    public Item selectOne(@PathVariable("id")Integer id){
        return userService.selectOne(id);
    }

    @OperateLog
    @GetMapping("")
    public List<User> selectList(){
        return userService.selectList();
    }

}
