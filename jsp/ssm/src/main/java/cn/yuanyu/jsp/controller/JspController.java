package cn.yuanyu.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author yuanyu
 */
@Controller
@RequestMapping("/")
public class JspController {

    // http://localhost:2000/jsp/ok
    @ResponseBody
    @GetMapping("/ok")
    public String ok() {
        return "ok";
    }

    // http://localhost:2000/jsp/kv
    @GetMapping("/kv")
    public ModelAndView kv() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("kv");
        mav.addObject("redirect_uri", "http://121.36.33.154:1000/heihei/index");
        return mav;
    }

}
