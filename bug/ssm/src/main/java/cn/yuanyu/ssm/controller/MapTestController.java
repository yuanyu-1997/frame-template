package cn.yuanyu.ssm.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author yuanyu
 */
@Controller
@RequestMapping("/")
public class MapTestController {

    // http://localhost:2000/ssm/map
    @RequestMapping("/map")
    public HashMap<String, Object> map() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("k", new HashMap<String, String>());
        res.put("version", "v1");
        return res;
    }

    // http://localhost:2000/ssm/map2
    @ResponseBody
    @RequestMapping("/map2")
    public HashMap<String, Object> map2() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("k", new HashMap<String, String>());
        return res;
    }

}
