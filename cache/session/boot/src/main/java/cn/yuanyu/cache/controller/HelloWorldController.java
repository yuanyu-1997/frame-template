package cn.yuanyu.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/session")
public class HelloWorldController {

    /**
     * http://localhost:6969/session/save
     */
    @GetMapping("/save")
    public String save(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = UUID.randomUUID().toString();
        session.setAttribute("token", token);
        return token;
    }
    /**
     * http://localhost:6969/session/get
     */
    @GetMapping("/get")
    public String get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("token");
    }

    @GetMapping("/remove")
    public String remove(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        return "ok";
    }


}
