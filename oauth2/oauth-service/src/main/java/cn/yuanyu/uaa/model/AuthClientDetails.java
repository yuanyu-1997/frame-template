package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 接入的客户端信息表
 */
@Data
public class AuthClientDetails {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 接入的客户端ID（唯一）
     */
    private String clientId;

    /**
     * 接入的客户端的名称
     */
    private String clientName;

    /**
     * 接入的客户端的密钥
     */
    private String clientSecret;

    /**
     * 回调地址
     */
    private String redirectUri;
    /**
     * 描述信息
     */
    private String description;
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
    /**
     * 0表示未开通；1表示正常使用；2表示已被禁用
     */
    private Integer status;

}