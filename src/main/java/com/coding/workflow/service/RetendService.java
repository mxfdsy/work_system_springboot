package com.coding.workflow.service;

import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import com.coding.workflow.entity.ReAttend;

/**
 * Created by 没想法的岁月 on 2018/5/10.
 */
public interface RetendService {

    void signReAttend(ReAttend reAttend);

    PageQueryBean listreAttend(QueryCondition condition);

    void updayFlag(Long userId);
}
