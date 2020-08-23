package cn.yuanyu.qrcode.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录二维码信息
 */
@Data
public class LoginQrcodeVO implements Serializable {
    /**
     * 二维码图片地址
     */
    private String qrcodeImgUrl;

    /**
     * 二维码Id
     */
    private String qrcodeId;
}
