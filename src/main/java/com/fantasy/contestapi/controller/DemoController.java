package com.fantasy.contestapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping(value = "hello")
    public String hello() {
        return "Spring boot Gradle Example";
    }
}
