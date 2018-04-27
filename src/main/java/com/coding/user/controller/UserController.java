package com.coding.user.controller;

import com.coding.user.entity.User;
import com.coding.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 平凡的世界 on 2018/4/25.
 */

@RequestMapping("/cww")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/user")
    public String user(){
        logger.info("hello");
        User user = new User();
        user.setUsername("333");
        user.setPassword("cww123456");
        user.setMobile("15058547187");
        user.setRealName("程武武");
        userService.createUser(user);
        return "user";
    }
}
