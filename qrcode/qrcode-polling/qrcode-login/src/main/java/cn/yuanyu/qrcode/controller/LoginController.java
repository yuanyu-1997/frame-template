package cn.yuanyu.qrcode.controller;

import cn.yuanyu.qrcode.service.impl.StringRedisServiceImpl;
import cn.yuanyu.qrcode.util.RedisKeyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    private StringRedisServiceImpl stringRedisService;

    @ResponseBody
    @GetMapping("/index")
    public String index(@RequestParam String token) {
        String key = RedisKeyBuilder.getAccessTokenUserIdKey(token);
        return "欢迎你 " + stringRedisService.getValue(key);
    }
}
