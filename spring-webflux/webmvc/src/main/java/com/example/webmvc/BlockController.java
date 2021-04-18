package com.example.webmvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author baker.yuan
 */
@Slf4j
@RestController
public class BlockController {


    /**
     * http://localhost:4500/block/3
     */
    @GetMapping("/block/{latency}")
    private String block(@PathVariable("latency") Integer latency) {
        log.info("webmvc start...");
        String result = createStr(latency);
        log.info("webmvc end...");
        return result;
    }

    /**
     * 阻塞
     */
    private String createStr(Integer blockTime) {
        try {
            TimeUnit.SECONDS.sleep(blockTime);
        } catch (InterruptedException ignored) {
        }
        return "block" + blockTime + "(ms)";
    }

}
