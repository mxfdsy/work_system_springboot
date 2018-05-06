package com.coding.attend.controller;

import com.coding.attend.entity.Attend;
import com.coding.attend.service.AttendService;
import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import com.coding.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    /**
     * 查询打卡记录
     * 考勤数据分页查询
     */
    @RequestMapping("/attendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition, HttpSession session){
        User user = (User)session.getAttribute("userinfo");
        String[] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getId());
        PageQueryBean result = attendServic.listAttend(condition);
        return  result;
    }
}
