package com.coding.commons.task;

import com.coding.attend.service.AttendService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 平凡的世界 on 2018/5/7.
 */
public class AttendCheckTask {
    @Autowired
    private AttendService attendService;

    public void checkAttend(){
        attendService.checkAttend();
    }
}
