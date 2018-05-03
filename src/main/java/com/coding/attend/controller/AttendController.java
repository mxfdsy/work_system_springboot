package com.coding.attend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
@Controller
@RequestMapping("attend")
public class AttendController {
    @RequestMapping
    public String toAttend(){
        return "attend";
    }
}
