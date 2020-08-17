package cn.yuanyu.uaa.model.sso;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 单点登录的回调域名的白名单
 */
@Data
public class SsoClientDetails {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 接入单点登录的系统名称
     */
    private String clientName;
    /**
     *
     */
    private String description;
    /**
     * 请求Token的回调URL
     */
    private String redirectUrl;
    /**
     * 注销URL
     */
    private String logoutUrl;
    /**
     * 0表示未开通；1表示正常使用；2表示已被禁用
     */
    private Integer status;

    public SsoClientDetails() {
    }

    public SsoClientDetails(String clientName, String description, String redirectUrl, String logoutUrl, Integer status) {
        this.clientName = clientName;
        this.description = description;
        this.redirectUrl = redirectUrl;
        this.logoutUrl = logoutUrl;
        this.status = status;
    }
}