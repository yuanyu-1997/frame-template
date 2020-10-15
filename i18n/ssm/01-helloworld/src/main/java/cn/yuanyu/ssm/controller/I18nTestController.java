package cn.yuanyu.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String toLoginPage() {
        return "login";
    }
    
    /**
     * 手动获取国际化信息
     */
    @Autowired
    private MessageSource messageSource;
    // http://localhost:46000/i18n/locale
    @GetMapping(value = "/locale", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String locale(Locale locale) {
        System.out.println("locale => " + locale);
        return messageSource.getMessage("msgTest", new Object[]{"占位符"}, locale);
    }
}
