package cn.yuanyu.uaa.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 用户对某个接入客户端的授权信息
 */
@Data
public class AuthClientUser {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    private Integer authClientId;
    /**
     *
     */
    private Integer userId;
    /**
     *
     */
    private Integer authScopeId;

}