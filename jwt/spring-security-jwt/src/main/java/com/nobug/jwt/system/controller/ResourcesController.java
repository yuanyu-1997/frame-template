package com.nobug.jwt.system.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yuanyu
 */
@RestController
public class ResourcesController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1() {
        return "访问资源1";
    }

    @GetMapping(value = "/r2")
    @PreAuthorize("hasAuthority('p2')")
    public String r2() {
        return "访问资源2";
    }



}