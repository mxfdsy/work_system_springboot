package com.coding.user.controller;

import com.coding.user.entity.User;
import com.coding.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
