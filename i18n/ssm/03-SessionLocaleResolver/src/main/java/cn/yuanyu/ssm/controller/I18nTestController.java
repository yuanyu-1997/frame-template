package cn.yuanyu.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;


/**
 * @author yuanyu
 */
@Controller
public class I18nTestController {
    /**
     * 跳转到登陆页面
     */
    @GetMapping("/toLoginPage")
    public String toLoginPage(@RequestParam(value = "locale", defaultValue = "zh_CN") String localeStr,
                              Locale locale, HttpSession session) {
        // zh_CN
        Locale l;
        // 如果带了locale参数就用参数指定的区域信息，如果没带就用请求头的
        if (localeStr != null && !"".equals(localeStr)) {
            l = new Locale(localeStr.split("_")[0], localeStr.split("_")[1]);
        } else {
            l = locale;
        }
        // 放入（覆盖）Session
        session.setAttribute(SessionLocaleResolver.class.getName() + ".LOCALE", l);
        return "login";
    }

}
