package cn.yuanyu.qrcode.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {

    /**
     *
     * @param authcode
     * @param request
     * @param response
     * @return
     */
    public ModelAndView getAccessToken(String authcode, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
