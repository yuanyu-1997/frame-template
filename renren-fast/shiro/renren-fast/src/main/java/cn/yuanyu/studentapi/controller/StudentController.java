package cn.yuanyu.studentapi.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * http://localhost:6969/student/add
     *
     * 添加商品
     */
    @GetMapping("/add")
    @RequiresPermissions("student:add")
    public String add() {
        return "student/add";
    }
    /**
     * 商品修改
     */
    @GetMapping("/update")
    @RequiresPermissions("student:update")
    public String update() {
        return "student/update";
    }
    /**
     * 商品删除
     */
    @GetMapping("/delete")
    @RequiresPermissions("student:delete")
    public String delete() {
        return "student/delete";
    }

    /**
     * 商品查询
     */
    @GetMapping("/select")
    @RequiresPermissions("student:select")
    public String select() {
        return "student/list";
    }

    /**
     * 商品列表
     */
    @GetMapping("/list")
    @RequiresPermissions("student:list")
    public String list() {
        return "student/list";
    }

    ///**
    // * 处理异常
    // */
    //@ExceptionHandler(value = {ShiroException.class})
    //public R permissionError(Throwable throwable) {
    //    return R.error(throwable.getMessage());
    //}
}
