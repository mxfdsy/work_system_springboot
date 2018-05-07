package com.coding.attend.dao;

import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    Attend selectTodaySignRecord(Long userId);

    int countByCondition(QueryCondition condition);

    List<Attend> selsectAttendPage(QueryCondition condition);

    Attend selectAbsenceTotalDay(Attend attend);
}