package com.taskmanager.frontendspringtm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/not-found")
    public String getNotFound(){
        return "/conta/not-found";
    }
}
