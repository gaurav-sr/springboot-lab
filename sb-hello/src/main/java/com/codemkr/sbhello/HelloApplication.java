package com.codemkr.sbhello;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@SpringBootApplication
public class HelloApplication {

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World! "+ LocalDate.now();
    }


    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}

