package com.taskmanager.frontendspringtm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "account/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "account/register";
    }

    @GetMapping("/profile")
    public String getProfilePage(){
        return "account/profile";
    }
}
