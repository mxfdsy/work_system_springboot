package com.coding.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 没想法的岁月 on 2018/4/27.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @RequestMapping
    public  String login(){
        return "login";
    }
}
