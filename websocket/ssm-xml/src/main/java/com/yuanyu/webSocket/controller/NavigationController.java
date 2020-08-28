package com.yuanyu.webSocket.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
public class NavigationController {

    // http://localhost:8084/socket
    @GetMapping("/socket")
    public String socket() {
        return "websocket";
    }


    // http://localhost:8084/cookie
    @GetMapping("/cookie")
    public String cookie() {
        return "cookie";
    }


    @ResponseBody
    @PostMapping("/a")
    public Object a(HttpServletRequest request, HttpSession session) {
        System.out.println("a => " + session + " : " + session.getId());
        HashMap<String, Object> res = new HashMap<>();
        res.put("sessionId", session.getId());
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            List<String> coolies = Arrays.stream(request.getCookies())
                    .map(item -> item.getName() + " : " + item.getValue())
                    .collect(Collectors.toList());
            res.put("cookies", coolies);
        }else {
            res.put("cookies", null);
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/b")
    public void b(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        System.out.println("b => " + session + " : " + session.getId());
        HashMap<String, Object> res = new HashMap<>();
        res.put("sessionId", session.getId());
        Cookie[] cookies = request.getCookies();
        if (cookies!=null) {
            List<String> coolies = Arrays.stream(request.getCookies())
                    .map(item -> item.getName() + " : " + item.getValue())
                    .collect(Collectors.toList());
            res.put("cookies", coolies);
        }else {
            res.put("cookies", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String serialize = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
        response.getWriter().write(serialize);
    }

}
