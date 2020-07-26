package cn.yuanyu.studentapi.controller.sys;

import cn.yuanyu.studentapi.common.utils.R;
import cn.yuanyu.studentapi.entity.sys.SysUserEntity;
import cn.yuanyu.studentapi.modules.sys.form.SysLoginForm;
import cn.yuanyu.studentapi.service.sys.SysUserService;
import cn.yuanyu.studentapi.service.sys.SysUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    public SysUserTokenService sysUserTokenService;

    //{
    //	"username":"张三",
    //	"password":"123456"
    //}

    /**
     * http://localhost:6969/sys/login
     * <p>
     * 登录
     */
    @PostMapping("/sys/login")
    public R login(@RequestBody SysLoginForm form) {
        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(form.getUsername());
        //账号不存在、密码错误
        if (user == null || !user.getPassword().equals(form.getPassword())) {
            return R.error("账号或密码不正确");
        }
        //账号锁定
        if (user.getStatus() == 0) {
            return R.error("账号已被锁定,请联系管理员");
        }
        //生成token，并保存到数据库
        return sysUserTokenService.createToken(user.getId());
    }

}
