package cn.yuanyu.qrcode.service.impl;

import cn.yuanyu.qrcode.service.ILoginQrcodeService;
import cn.yuanyu.qrcode.service.ITokenService;
import cn.yuanyu.qrcode.util.MatrixToImageWriter;
import cn.yuanyu.qrcode.util.RedisKeyBuilder;
import cn.yuanyu.qrcode.vo.AccessToken;
import cn.yuanyu.qrcode.vo.LoginQrcodeVO;
import cn.yuanyu.qrcode.vo.Response;
import cn.yuanyu.qrcode.vo.ResponseCode;
import com.aliyun.oss.OSS;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.xuanner.seq.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Service
public class LoginQrcodeServiceImpl implements ILoginQrcodeService {
    // 操作redis
    @Autowired
    private StringRedisServiceImpl redisService;
    // 存储二维码
    @Autowired
    private OSS ossClient;
    @Autowired
    private ITokenService tokenService;
    //
    @Value("${ali.oss.bucketName}")
    private String bucketName;
    @Value("${ali.oss.baseUrl}")
    private String aliOssBaseUrl;
    // 二维码包含的登录地址
    @Value("${qrcode.login-url}")
    private String qrcodeLoginUrl;
    @Value("${qrcode.width}")
    private Integer qrcodeWidth;
    @Value("${qrcode.height}")
    private Integer qrcodeHeight;
    @Value("${qrcode.format}")
    private String qrcodeFormat;

    /**
     * 生成登录二维码图片并且返回图片地址
     */
    // 建议二维码生成，前端去做，返回登陆连接即可
    @Override
    public Response<LoginQrcodeVO> createLoginQrcode() {
        // 1.生成二维码ID
        String qrcodeId = UUIDUtils.uuid();
        String qrcodePngName = qrcodeId + "." + qrcodeFormat;
        // 按日期分离
        String dir = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "/";
        // 二维码图片的访问路径
        String qrcodeImgUrl = aliOssBaseUrl + dir + qrcodePngName;
        log.info("二维码访问路径 => {}", qrcodeImgUrl);
        // 2.设置二维码内容
        String qrcodeContent = qrcodeLoginUrl;
        log.info("二维码请求地址 => {}", qrcodeContent);
        try {
            HashMap<EncodeHintType, String> hints = new HashMap<>(1);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(qrcodeContent, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);
            BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
            // 3.生成二维码
            // 临时文件
            File tempFile = new File("./temp-" + qrcodePngName);
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            ImageIO.write(image, qrcodeFormat, tempFile);
            MatrixToImageWriter.writeToFile(bitMatrix, qrcodeFormat, tempFile);
            // 上传到阿里云
            ossClient.putObject(bucketName, dir + qrcodePngName, tempFile);
            tempFile.delete();
            // 4.写入Redis 缓存
            String loginQrcodeKey = RedisKeyBuilder.getLoginqrcodeKey(qrcodeId);
            // 设置登录二维码的value为空表示没有登录（3分钟有效）
            redisService.cacheValue(loginQrcodeKey, "", 180);
            // 5.返回数据
            LoginQrcodeVO loginQrcode = new LoginQrcodeVO();
            loginQrcode.setQrcodeId(qrcodeId);
            loginQrcode.setQrcodeImgUrl(qrcodeImgUrl);
            return Response.successResult("生成二维码成功", loginQrcode);
        } catch (Exception e) {
            log.error("生成二维码发生异常，异常信息：{}", e.getMessage());
            return Response.failResult("生成二维码失败");
        }
    }

    /**
     * 扫描二维码登录
     *
     * @param qrcodeId 二维码ID
     * @param userId   用户ID
     */
    @Override
    public Response<Boolean> qrcodeLogin(String qrcodeId, String userId) {
        String loginQrcodeKey = RedisKeyBuilder.getLoginqrcodeKey(qrcodeId);
        if (redisService.containsValueKey(loginQrcodeKey)) {
            boolean flag = redisService.cacheValue(loginQrcodeKey, userId, 180);
            if (flag) {
                log.info("用户扫描登陆成功 => {}", userId);
                return Response.successResult("二维码登录成功", flag);
            } else {
                log.info("二维码登录失败，qrcodeId={}", qrcodeId);
                return Response.successResult("二维码登录失败", flag);
            }
        } else {
            log.info("二维码已不存在，qrcodeId={}", qrcodeId);
            return Response.failResult("二维码登录失败", false);
        }
    }

    /**
     * 查询二维码的登录状态
     *
     * @param qrcodeId 二维码ID
     */
    @Override
    public Response<AccessToken> getLoginQrcodeStatus(String qrcodeId) {
        String loginQrcodeKey = RedisKeyBuilder.getLoginqrcodeKey(qrcodeId);
        String userId = redisService.getValue(loginQrcodeKey);
        if (StringUtils.isBlank(userId)) {
            return Response.failResult("二维码还未登录");
        }
        // 认证通过后，生成访问令牌
        // ...
        Response<AccessToken> response = tokenService.createAccessToken(userId);
        if (ResponseCode.SUCCESS.equals(response.getCode())) {
            AccessToken accessTokenObj = response.getData();
            return Response.successResult("二维码已登录", accessTokenObj);
        } else {
            log.error("二维码已登录，但生成访问令牌失败");
            return Response.failResult("二维码已登录，但是生成访问令牌失败");
        }
    }

}
