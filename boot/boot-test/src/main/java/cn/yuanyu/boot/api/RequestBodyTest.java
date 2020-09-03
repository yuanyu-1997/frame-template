package cn.yuanyu.boot.api;

import cn.yuanyu.boot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/body")
public class RequestBodyTest {

    // http://localhost:5000/bt/body/map
    // {"name":"xxx","age":"18"}
    @PostMapping("/map")
    public Map<String, String> map(@RequestBody Map<String, String> map) {
        return map;
    }

    // http://localhost:5000/bt/body/obj
    @PostMapping("/obj")
    public User obj(@RequestBody User user) {
        return user;
    }


}
