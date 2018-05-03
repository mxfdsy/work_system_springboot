package com.coding.attend.controller;

import com.coding.attend.entity.Attend;
import com.coding.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
@Controller
@RequestMapping("attend")
public class AttendController {

    @Autowired
    private AttendService attendServic;

    @RequestMapping
    public String toAttend() {
        return "attend";
    }
    @RequestMapping("sigin")
    @ResponseBody
    public String signAttend(@RequestBody Attend attend) {
        attendServic.signAttend(attend);
        return "succ";
    }
}
