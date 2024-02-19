package com.gscode.springboot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@EnableAutoConfiguration
public class CacheApp {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!"+ new Date();
    }

    public static void main(String[] args) {
        SpringApplication.run(CacheApp.class, args);
    }
}

