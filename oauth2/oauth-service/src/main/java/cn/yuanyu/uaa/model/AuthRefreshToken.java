package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * Refresh Token信息表
 */
@Data
public class AuthRefreshToken {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 关联的表auth_access_token对应的Access Token记录
     */
    private Integer tokenId;

    /**
     * Refresh Token
     */
    private String refreshToken;
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