package cn.yuanyu.submit.controller;

import cn.yuanyu.submit.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/")
public class SubmitController {


    /**
     * http://localhost:6969/token/submit
     */
    @PostMapping("/submit")
    public Object sub(@RequestParam Map<String, String> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = "";
        if (session.getAttribute("token") != null) {
            token = (String) session.getAttribute("token");
        }
        //对比用户提交的token和session里面的token
        if (!token.equals(params.get("token"))) {
            return R.error("token error");
        }
        //删除绑定的令牌
        session.removeAttribute("token");
        //some code
        return R.ok();
    }

}
