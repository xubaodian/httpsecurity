package com.server.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RouterController {
    @RequestMapping("/")
    public String helloword() {
        return  "hello world";
    }
}
