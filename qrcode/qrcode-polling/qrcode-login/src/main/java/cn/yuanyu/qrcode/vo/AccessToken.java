package cn.yuanyu.qrcode.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccessToken extends SundataObject {
    private static final long serialVersionUID = -5119046968646554604L;

    /**
     * 访问令牌初始化过期时间 2小时 = 2*3600
     */
    public static final int ACCESSTOKEN_EXPIREIN = 7200;
    /**
     * 访问授权令牌
     */
    private String accessToken;
    /**
     * 访问过期时间
     */
    private int expireIn;
    /**
     * 刷新token
     */
    private String refreshToken;
    /**
     * 用户Id
     */
    private String openId;

    /**
     * 账号状态
     *      0 : 已禁用
     *      1 : 正常
     *      2 : 未激活
     */
    private String state;
}
