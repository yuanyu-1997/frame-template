package cn.yuanyu.qrcode.controller;


import cn.yuanyu.qrcode.service.LoginService;
import cn.yuanyu.qrcode.util.*;
import cn.yuanyu.qrcode.websocket.WebSocketHandler;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Controller
@CrossOrigin
@RequestMapping(value = "/login/qrcode")
public class QrCodeLoginController {

    // 和网页通信
    @Autowired
    private WebSocketHandler webSocketHandler;

    @Autowired
    private LoginService loginService;

    //
    private final Map<String, String> LOGIN_SUCCESS_USERS = new HashMap<>();

    /**
     * 1.获取二维码内容
     */
    // http://127.0.0.1:30000/heihei/login/qrcode/loginuuid
    @ResponseBody
    @GetMapping(value = "/loginuuid")
    public R getLoginUuid(HttpServletRequest request) {
        String sessionid = StringAssistor.randomString(6);
        HttpSession session = request.getSession();
        // 这里往session里面存入了数据
        session.setAttribute("sessionid", sessionid);
        // 二维码内容
        String loginUrl = "http://127.0.0.1:30000/heihei/login/qrcode/callback" + "?sessionid=" + sessionid;
        return R.ok()
                .put("loginurl", loginUrl)
                .put("sessionid", sessionid);
    }


    /**
     * 手机app解析二维码，回调这个地址
     * @param type     扫描状态（1 二维码过期  2 二维码被扫描  3 登录成功
     * @param username 用户唯一标识（用户已经在app登陆，由app获取）
     * @param code
     * @param state    二维码唯一标识（来自二维码的回传）
     */
    @ResponseBody
    @PostMapping(value = "/callback")
    public R callback(HttpServletRequest request) {
        Map<String, String> params = ModelAndViewUtil.fetchParameterMap(request);
        log.info("二维码扫描回调 => " + params);
        String type = params.get("type");
        String username = params.get("username");
        String authCode = params.get("code");
        String sessionid = params.get("state");

        boolean success = false;
        if (StringUtils.isNotBlank(type)) {
            switch (type) {
                case "1": { // 二维码被扫描，用户未确认登录
                    if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(sessionid)) {
                        // 扫描成功
                        R res = R.ok()
                                .put("msgtype", 2)
                                .put("sessionid", sessionid);
                        if (webSocketHandler.sendMessageToUser(sessionid, new TextMessage(JSON.toJSONString(res)))) {
                            log.info("用户：" + username + "扫描二维码成功!sessionid=" + sessionid);
                            success = true;
                        }
                    }
                    break;
                }
                case "2": { // 用户确认登录
                    if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(sessionid) && StringUtils.isNotBlank(authCode)) {
                        R res = R.ok()
                                .put("msgtype", 3) // 登录成功
                                .put("authcode", authCode)
                                .put("username", username)
                                .put("sessionid", sessionid);
                        if (webSocketHandler.sendMessageToUser(sessionid, new TextMessage(JSON.toJSONString(res)))) {
                            LOGIN_SUCCESS_USERS.put(username, sessionid);
                            log.info("用户：" + username + "登录成功!sessionid=" + sessionid);
                            success = true;
                        }
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return success ? R.ok() : R.error();
    }


    /**
     * 二维码已扫描，api调用
     */
    @ResponseBody
    @RequestMapping("/scansuccess.do")
    public Object scansuccess(HttpServletRequest request) {
        String username = request.getParameter("username");
        String sessionid = request.getParameter("uuid");
        log.info("二维码扫描api调用,username:" + username + " sessionid:" + sessionid);
        boolean success = false;
        if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(sessionid)) {
            // 扫描成功
            R res = R.ok()
                    .put("msgtype", 2)
                    .put("sessionid", sessionid);
            if (webSocketHandler.sendMessageToUser(sessionid, new TextMessage(JSON.toJSONString(res)))) {
                log.info("用户：" + username + "扫描二维码成功!sessionid=" + sessionid);
                success = true;
            }
        }
        return success ? R.ok() : R.error();
    }

    /**
     * 确认登录，api调用
     */
    @ResponseBody
    @RequestMapping("/loginsuccess.do")
    public Object loginsuccess(HttpServletRequest request) {
        String username = request.getParameter("username");
        String sessionid = request.getParameter("uuid");
        String tokenid = request.getParameter("tokenid");
        log.info("确认登录api调用,username:" + username + " sessionid:" + sessionid);
        boolean success = false;
        if (StringUtils.isNotBlank(username) || StringUtils.isNotBlank(sessionid) || StringUtils.isNotBlank(tokenid)) {
            // 登录成功
            R res = R.ok()
                    .put("msgtype", 3)
                    .put("username", username)
                    .put("sessionid", sessionid)
                    .put("tokenid", tokenid);
            if (webSocketHandler.sendMessageToUser(sessionid, new TextMessage(JSON.toJSONString(res)))) {
                // 存
                LOGIN_SUCCESS_USERS.put(username, sessionid);
                log.info("用户：" + username + "登录成功!sessionid=" + sessionid);
                success = true;
            }

        }
        return success ? R.ok() : R.error();
    }


    /**
     * 二维码登录成功，PC端请求访问主页
     */
    @RequestMapping(value = "/accessmain.do")
    public ModelAndView accessmain(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String sessionid = request.getParameter("sessionid");
        String authcode = request.getParameter("authcode");
        log.info("用户：" + username + "请求访问主页");
        HttpSession session = request.getSession();
        CookieUtil.remove(request, response, Constant.COOKIE_SDP_SESSIONID);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(sessionid)) {
            return new ModelAndView("authentication/login");
        }
        // 取
        if (sessionid.equals(LOGIN_SUCCESS_USERS.get(username))) {
            LOGIN_SUCCESS_USERS.remove(username);
            return getAccessToken(request, authcode, response);
        } else {
            log.info("用户：" + username + "非法请求");
        }
        return new ModelAndView("authentication/login");
    }

    public ModelAndView getAccessToken(HttpServletRequest request, String authcode, HttpServletResponse response) {
        return loginService.getAccessToken(authcode, request, response);
    }

}
