package cn.yuanyu.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * http://localhost:6969/user
 *
 * @author yuanyu
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * http://localhost:6969/user/save
     * 保存
     */
    @GetMapping(value = "/save")
    @PreAuthorize("hasAuthority('res:user:save')")
    public String save() {
        return "保存用户成功";
    }

    /**
     * 删除
     */
    @GetMapping(value = "/delete")
    @PreAuthorize("hasAuthority('res:user:delete')")
    public String delete() {
        return "删除用户成功";
    }


    /**
     * 修改
     */
    @GetMapping(value = "/update")
    @PreAuthorize("hasAuthority('res:user:update')")
    public String update() {
        return "修改用户成功";
    }

    /**
     * 根据id查询用户
     */
    @GetMapping(value = "/info")
    @PreAuthorize("hasAuthority('res:user:info')")
    public String info() {
        return "用户张三";
    }

    /**
     * 返回用户列表
     */
    @GetMapping(value = "/list")
    @PreAuthorize("hasAuthority('res:user:list')")
    public String list() {
        return "用户list";
    }
}
