package com.example.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author baker.yuan
 */
@RestController
public class HelloController {

    /**
     * http://localhost:5000/webflux/1
     */
    @GetMapping("/webflux/{latency}")
    public Mono<String> hello(@PathVariable int latency) {
        return Mono.just("webflux").delayElement(Duration.ofMillis(latency));
    }

}