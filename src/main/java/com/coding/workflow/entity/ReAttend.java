package com.coding.workflow.entity;

import java.io.Serializable;
import java.util.Date;

public class ReAttend implements Serializable {
    private Long id;

    private Long userId;

    private Date reAttendDate;

    private Date reAttendMoring;

    private Date reAttendEvening;

    private String userCurrentstatus;

    private String currentprogrance;

    private String approveflag;

    private String comment;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getReAttendDate() {
        return reAttendDate;
    }

    public void setReAttendDate(Date reAttendDate) {
        this.reAttendDate = reAttendDate;
    }

    public Date getReAttendMoring() {
        return reAttendMoring;
    }

    public void setReAttendMoring(Date reAttendMoring) {
        this.reAttendMoring = reAttendMoring;
    }

    public Date getReAttendEvening() {
        return reAttendEvening;
    }

    public void setReAttendEvening(Date reAttendEvening) {
        this.reAttendEvening = reAttendEvening;
    }

    public String getUserCurrentstatus() {
        return userCurrentstatus;
    }

    public void setUserCurrentstatus(String userCurrentstatus) {
        this.userCurrentstatus = userCurrentstatus == null ? null : userCurrentstatus.trim();
    }

    public String getCurrentprogrance() {
        return currentprogrance;
    }

    public void setCurrentprogrance(String currentprogrance) {
        this.currentprogrance = currentprogrance == null ? null : currentprogrance.trim();
    }

    public String getApproveflag() {
        return approveflag;
    }

    public void setApproveflag(String approveflag) {
        this.approveflag = approveflag == null ? null : approveflag.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}