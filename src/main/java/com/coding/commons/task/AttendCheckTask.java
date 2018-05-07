package com.coding.commons.task;

import com.coding.attend.entity.Attend;
import com.coding.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 平凡的世界 on 2018/5/7.
 */
@Controller
@RequestMapping("text")
public class AttendCheckTask {
    @Autowired
    private AttendService attendService;

    @ResponseBody
    @RequestMapping("te")
    public void checkAttend(){
        Attend attend = new Attend();
        attend.setUserId((long) 1);
        attendService.checkAttend(attend);
    }
}
