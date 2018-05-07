package com.coding.attend.service;

import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
public interface AttendService {
    void signAttend(Attend attend);

    PageQueryBean listAttend(QueryCondition condition);

    void checkAttend();
}
