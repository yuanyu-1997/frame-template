package cn.yuanyu.uaa.model.sso;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 单点登录的Refresh Token信息表
 */
@Data
public class SsoRefreshToken {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 表 sso_access_token 对应的 Access Token 记录（sso_access_token主键）
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