package com.coding.workflow.service;

import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import com.coding.workflow.dao.ReAttendMapper;
import com.coding.workflow.entity.ReAttend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 没想法的岁月 on 2018/5/10.
 */
@Service
public class RetendServiceImp implements RetendService {

    @Autowired
    private ReAttendMapper reAttendMapper;

    @Override
    public void signReAttend(ReAttend reAttend) {
        reAttendMapper.insertSelective(reAttend);
    }

    @Override
    public PageQueryBean listreAttend(QueryCondition condition) {
        //如果没有查询到数据  我们就不用去查询调用查询sql去查
        int count = reAttendMapper.countByCondition(condition);
        PageQueryBean pageresult = new PageQueryBean();
        if (count > 0) {
            //这是准备返回给前端的信息
            pageresult.setTotalRows(count);
            pageresult.setCurrentPage(condition.getCurrentPage());
            pageresult.setPageSize(condition.getPageSize());
            //查询数据库
            List<Attend> attendList = reAttendMapper.selsectReAttendPage(condition);
            pageresult.setItems(attendList);
        }
        return pageresult;
    }

    @Override
    public void updayFlag(Long userId) {
        reAttendMapper.updateReAttendRecord(userId);
    }
}
