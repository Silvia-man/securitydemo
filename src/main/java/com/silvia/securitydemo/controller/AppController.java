package com.silvia.securitydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Silvia
 * @Date: 2019/11/8  20:49
 */

@RestController
@RequestMapping("/app/api")
public class AppController {
    @GetMapping("hello")
    public String hello() {
        return "hello, app";
    }
}
