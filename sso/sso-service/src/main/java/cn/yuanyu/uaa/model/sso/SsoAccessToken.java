package cn.yuanyu.uaa.model.sso;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 单点登录的Access Token信息表
 */
@Data
public class SsoAccessToken {
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
     * 用户名
     */
    private String userName;
    /**
     * 用户来源IP
     */
    private String ip;
    /**
     *
     */
    private Integer clientId;
    /**
     * 表示这个Token用于什么渠道，比如：官网、APP1、APP2等等
     */
    private String channel;
    /**
     * 过期时间戳
     */
    private Long expiresIn;
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