package com.coding.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 没想法的岁月 on 2018/4/27.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping
    public  String login(){
        return "login";
    }

    @RequestMapping("/check")
    //返回的是主体信息而不是url
    @ResponseBody
    public String checkLogin(HttpServletRequest request) {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        //查询数据库
        //校验成功
        //用户存session
        //进入首页
        //失败 提示
        return "login_succ";
    }
}

