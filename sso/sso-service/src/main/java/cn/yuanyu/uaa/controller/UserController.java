package cn.yuanyu.uaa.controller;

import cn.yuanyu.uaa.common.Constants;
import cn.yuanyu.uaa.model.User;
import cn.yuanyu.uaa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     */
    // http://localhost:8000/uaa/register
    // {"username":"zhangsan","password":"123456","mobile":"17783649166","email":"zhangsan@vip.com"}
    @ResponseBody
    @PostMapping(value = "/register")
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>(2);
        boolean registerRet = userService.register(user);
        if (registerRet) {
            result.put("code", "200");
        } else {
            result.put("code", "500");
            result.put("msg", "注册失败");
        }
        return result;
    }

    /**
     * 登录页面
     */
    // http://localhost:8000/uaa/login?redirect_uri=www.baidu.com
    @RequestMapping("/login")
    public ModelAndView loginPage(@RequestParam(value = "redirect", required = false) String redirectUrl, HttpSession session) {
        log.info("登录页面，从定向地址 => {}", redirectUrl);
        if (StringUtils.isNoneBlank(redirectUrl)) {
            // 将回调地址添加到session中
            session.setAttribute(Constants.SESSION_LOGIN_REDIRECT_URL, redirectUrl);
        }
        return new ModelAndView("login");
    }

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     */
    @ResponseBody
    @PostMapping("/check")
    public Map<String, Object> check(String username, String password, HttpSession session) {
        log.info("登录验证...");
        Map<String, Object> result = new HashMap<>(2);
        if (StringUtils.isNoneBlank(username) && StringUtils.isNoneBlank(password)) {
            // 1.登录验证
            Map<String, Object> checkMap = userService.checkLogin(username, password);
            Boolean loginResult = (Boolean) checkMap.get("result");
            User correctUser = (User) checkMap.get("user");
            // 登录验证通过
            if (loginResult != null && loginResult) {
                // 2.session中添加用户信息
                session.setAttribute(Constants.SESSION_USER, correctUser);
                // 3.返回给页面的数据
                result.put("code", 200);
                // 登录成功之后的回调地址
                String redirectUrl = (String) session.getAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
                session.removeAttribute(Constants.SESSION_LOGIN_REDIRECT_URL);
                if (StringUtils.isNoneBlank(redirectUrl)) {
                    result.put("redirect_uri", redirectUrl);
                }

            } else {
                if (correctUser.getStatus() == 0) {
                    result.put("msg", "该用户还未开通！");
                } else if (correctUser.getStatus() == 3) {
                    result.put("msg", "该用户已被管理员禁用！");
                } else {
                    result.put("msg", "用户名或密码错误！");
                }
            }
        } else {
            result.put("msg", "请求参数不能为空！");
        }
        return result;
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_USER);
        return new ModelAndView("redirect:/login");
    }

    /**
     * 用户首页
     */
    @RequestMapping("/user/userIndex")
    public String userIndex() {
        return "userIndex";
    }
}
