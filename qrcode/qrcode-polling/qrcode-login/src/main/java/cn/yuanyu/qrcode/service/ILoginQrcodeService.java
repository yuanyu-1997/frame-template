package cn.yuanyu.qrcode.service;


import cn.yuanyu.qrcode.vo.AccessToken;
import cn.yuanyu.qrcode.vo.LoginQrcodeVO;
import cn.yuanyu.qrcode.vo.Response;


/**
 * 登录二维码业务逻辑
 */
public interface ILoginQrcodeService {

    /**
     * 生成登录二维码图片并且返回图片地址
     */
    Response<LoginQrcodeVO> createLoginQrcode();

    /**
     * 扫描二维码登录
     *
     * @param qrcodeId 二维码ID
     * @param userId   用户ID
     */
    Response<Boolean> qrcodeLogin(String qrcodeId, String userId);


    /**
     * 查询二维码的登录状态
     *
     * @param qrcodeId 二维码ID
     */
    Response<AccessToken> getLoginQrcodeStatus(String qrcodeId);

}
