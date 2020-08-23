package cn.yuanyu.qrcode.controller;

import cn.yuanyu.qrcode.service.ILoginQrcodeService;
import cn.yuanyu.qrcode.vo.AccessToken;
import cn.yuanyu.qrcode.vo.LoginQrcodeVO;
import cn.yuanyu.qrcode.vo.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/qrcode")
public class QrcodeLoginController {

    @Autowired
    private ILoginQrcodeService loginQrcodeService;

    /**
     * 生成登录的二维码
     */
    // http://localhost:30000/heihei/qrcode/create
    @PostMapping("/create")
    public Response<LoginQrcodeVO> createLoginQrcode() {
        return loginQrcodeService.createLoginQrcode();
    }

    /**
     * 扫码登录
     * 1、获取登录用户信息
     * 2、拼接登录二维码
     */
    // http://localhost:30000/heihei/qrcode/login?qrcodeId=&userId=
    @PostMapping("/login")
    public Response<Boolean> qrcodeLogin(String qrcodeId, String userId) {
        if (StringUtils.isBlank(qrcodeId) || StringUtils.isBlank(userId)) {
            return Response.failResult("二维码Id和用户Id不能为空");
        }
        return loginQrcodeService.qrcodeLogin(qrcodeId, userId);
    }

    /**
     * 验证二维码是否已登录
     */
    // http://localhost:30000/heihei/qrcode/isLogined?qrcodeId=
    @PostMapping("isLogined")
    public Response<AccessToken> checkQrcodeIsLogined(@RequestParam String qrcodeId) {
        if (StringUtils.isBlank(qrcodeId)) {
            return Response.failResult("二维码Id不能为空");
        }
        return loginQrcodeService.getLoginQrcodeStatus(qrcodeId);
    }

}
