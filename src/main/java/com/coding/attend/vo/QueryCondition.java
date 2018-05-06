package com.coding.attend.vo;

import com.coding.commons.page.PageQueryBean;

/**
 * Created by 平凡的世界 on 2018/5/6.
 */
public class QueryCondition extends PageQueryBean {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
