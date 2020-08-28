package cn.yuanyu.websocket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

    //http://localhost:3000/heihei/idx
    @GetMapping("/idx")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("key","success");
        return mav;
    }
}
