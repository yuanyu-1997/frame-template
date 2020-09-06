package cn.yuanyu.db.pojo;

import lombok.Data;

import java.util.Date;

/**
 * 用户表
 */
@Data
public class User {
    /**
     *
     */
    private Integer id;
    /**
     *
     */
    private String username;
    /**
     *
     */
    private String password;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Date birthday;
    /**
     *
     */
    private Character sex;
    /**
     *
     */
    private String email;
    /**
     *
     */
    private String phone;
    /**
     *
     */
    private String qq;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


}
