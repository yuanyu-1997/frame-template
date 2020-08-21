package cn.yuanyu.websocket.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    // http://localhost:3000/websocket/
    @GetMapping("/")
    public String idx(){
        return "ok";
    }
}
