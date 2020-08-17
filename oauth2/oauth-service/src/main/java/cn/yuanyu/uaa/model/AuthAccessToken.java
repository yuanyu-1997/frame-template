package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * Access Token信息表
 */
@Data
public class AuthAccessToken {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * Access Token
     */
    private String accessToken;

    /**
     * 关联的用户ID
     */
    private Integer userId;
    /**
     * 关联的用户名
     */
    private String userName;

    /**
     * 接入的客户端ID
     */
    private Integer clientId;

    /**
     * 过期时间戳
     */
    private Long expiresIn;

    /**
     * 授权类型，比如：authorization_code
     */
    private String grantType;
    /**
     * 可被访问的用户的权限范围，比如：basic、super
     */
    private String scope;
    /**
     * 创建用户
     */
    private Integer createUser;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新用户
     */
    private Integer updateUser;
    /**
     * 最后更新时间
     */
    private Date updateTime;

}