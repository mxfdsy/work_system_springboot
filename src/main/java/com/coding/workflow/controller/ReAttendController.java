package com.coding.workflow.controller;

import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import com.coding.user.entity.User;
import com.coding.workflow.entity.ReAttend;
import com.coding.workflow.service.RetendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by 没想法的岁月 on 2018/5/10.
 */
@Controller
@RequestMapping("/reAttend")
public class ReAttendController {

    @RequestMapping
    public String toReAttend(){
        return "reAttend";
    }

    @Autowired
    private RetendService retendService;

    @ResponseBody
    @RequestMapping("/start")
    public String startReTtend(@RequestBody ReAttend reAttend){
        retendService.signReAttend(reAttend);
        return "提交申请成功" ;
    }
    /**
     * 查询打卡记录
     * 考勤数据分页查询
     */
    @RequestMapping("/reattendList")
    @ResponseBody
    public PageQueryBean listAttend(QueryCondition condition, HttpSession session){
        User user = (User)session.getAttribute("userinfo");
        String[] rangeDate = condition.getRangeDate().split("/");
        condition.setStartDate(rangeDate[0]);
        condition.setEndDate(rangeDate[1]);
        condition.setUserId(user.getUser_id());
        PageQueryBean result = retendService.listreAttend(condition);
        return  result;
    }
}
