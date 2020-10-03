package cn.yuanyu.ssm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyu
 */
@RestController
@RequestMapping("/")
public class HelloWorldController {

    // http://localhost:45000/ssm/str
    @GetMapping("/str")
    public String str() {
        return "hello world.";
    }

    // http://localhost:45000/ssm/jsp
    @GetMapping("/jsp")
    public String index() {
        return "index";
    }


    //// http://localhost:45000/ssm/map
    //@GetMapping("/map")
    //public HashMap<String, String> map() {
    //    HashMap<String, String> res = new HashMap<>();
    //    res.put("code", "2000");
    //    return res;
    //}

//    // http://localhost:45000/ssm/map
//    @ResponseBody
//    @GetMapping("/map")
//    public HashMap<String, String> map() {
//        HashMap<String, String> res = new HashMap<>();
//        res.put("code", "2000");
//        return res;
//    }

}
