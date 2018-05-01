package com.coding.login.controller;

import com.coding.user.entity.User;
import com.coding.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 没想法的岁月 on 2018/4/27.
 */
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * @author 没想法的岁月
     * @Date 2018/5/1 9:29
     * @Description 页面登录
     */
    @RequestMapping
    public String login() {
        return "login";
    }

    /**
     * @author 没想法的岁月
     * @Date 2018/5/1 9:30
     * @Description 校验登录
     */
    @RequestMapping("/check")
    @ResponseBody
    public String checkLogin(HttpServletRequest httpServletRequest) {

        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        //查询数据库 如果查询到数据  调用md5校验密码
        User user = userService.findUserByUserName(username);
        //如果成功(存session 进入首页) 失败 提示失败
        //
        return "login_succc";
    }

    /**
     * @author 没想法的岁月
     * @Date 2018/5/1 9:45
     * @Description 注册功能
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestBody User user) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        userService.nLoginUser(user);
        return "succ";
    }

}

