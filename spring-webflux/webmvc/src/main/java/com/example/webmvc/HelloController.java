package com.example.webmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author baker.yuan
 */
@RestController
public class HelloController {


    @GetMapping("/webmvc/{latency}")
    public String hello(@PathVariable("latency") long latency) {
        try {
            TimeUnit.MILLISECONDS.sleep(latency);
        } catch (InterruptedException ignored) {
        }
        return "webmvc";
    }

}