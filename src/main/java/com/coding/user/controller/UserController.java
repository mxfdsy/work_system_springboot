package com.coding.user.controller;

import com.coding.user.entity.User;
import com.coding.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 平凡的世界 on 2018/4/25.
 */

@RequestMapping("/cww")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/home")
    public String user() throws UnsupportedEncodingException, NoSuchAlgorithmException {

        return "home";
    }
    /**
     * @author  没想法的岁月
     * @Date 2018/5/3 17:20
     * @Description 获取用户信息
     */
    @RequestMapping("/userinfo")
    @ResponseBody
    public  User getUser(HttpSession session){
        User user = (User)session.getAttribute("userinfo");
        return user;
    }

}
