package cn.yuanyu.service.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author yuanyu
 */
@Data
public class UserModel implements Serializable {
    private static final long serialVersionUID = 103303214536004879L;
    /**
     * 主键ID
     */
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 100, message = "年龄范围不对[0, 100]")
    private Integer age;
    /**
     * 邮箱
     */
    @Email
    private String email;

    public UserModel() {
    }

    public UserModel(String username, String password, String name, Integer age, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
    }
}