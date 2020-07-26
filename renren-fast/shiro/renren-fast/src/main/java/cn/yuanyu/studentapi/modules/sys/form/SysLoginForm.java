package cn.yuanyu.studentapi.modules.sys.form;

import lombok.Data;

/**
 * 登录表单
 *
 * @author yuanyu
 */
@Data
public class SysLoginForm {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}