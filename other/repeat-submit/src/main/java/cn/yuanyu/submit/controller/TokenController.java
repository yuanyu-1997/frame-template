package cn.yuanyu.submit.controller;

import cn.yuanyu.submit.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    /**
     * http://localhost:6969/token/save
     */
    @PostMapping("/save")
    public R save(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = UUID.randomUUID().toString();
        session.setAttribute("token", token);
        return R.ok().put("token", token);
    }


    @PostMapping("/remove")
    public R remove(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        return R.ok();
    }

}
