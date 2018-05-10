package com.coding.workflow.dao;

import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;
import com.coding.workflow.entity.ReAttend;

import java.util.List;

public interface ReAttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReAttend record);

    int insertSelective(ReAttend record);

    ReAttend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReAttend record);

    int updateByPrimaryKey(ReAttend record);

    int countByCondition(QueryCondition condition);

    List<Attend> selsectReAttendPage(QueryCondition condition);
}