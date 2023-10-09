package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linyida
 * @version 1.0
 * @created 2023/9/19.
 */
@RestController
public class DemoController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }

}
