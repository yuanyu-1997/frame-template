package com.example.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author baker.yuan
 */
@Slf4j
@RestController
public class BlockController {

    /**
     * http://localhost:5000/block/3
     * <p>
     * WebFlux(返回的是Mono)
     */
    @GetMapping("/block/{latency}")
    private Mono<String> block(@PathVariable("latency") Integer latency) {
        log.info("webflux start...");
        Mono<String> result = Mono.fromSupplier(() -> createStr(latency));
        log.info("webflux end...");
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
        return "block" + blockTime + "(s)";
    }

}
