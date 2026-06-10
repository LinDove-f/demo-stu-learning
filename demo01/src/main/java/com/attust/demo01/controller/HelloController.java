package com.attust.demo01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fqlstart
 * @create 2026-06-03-17:11
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/test")
    public String hello(){
        return "hello";
    }
}
