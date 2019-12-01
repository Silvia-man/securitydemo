package com.silvia.securitydemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.silvia.securitydemo.mapper")
public class SecuritydemoApplication {

    @GetMapping("/")
    public String hello() {
        return "hello, spring security";
    }

    public static void main(String[] args) {
        SpringApplication.run( SecuritydemoApplication.class , args);
    }

}
