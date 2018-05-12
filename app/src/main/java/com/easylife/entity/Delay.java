package com.easylife.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class Delay extends BmobObject {
    public static final Character STATE_TODO = 0;
    public static final Character STATE_FINISH = 1;
    public static final Character STATE_FAILED = 2;
    private static final Character URGENCY_DEGREE_HIGH = 3;
    private static final Character URGENCY_DEGREE_LOW = 1;
    private static final Character URGENCY_DEGREE_NORMAL = 2;
    private static final Character IMPORTANCE_DEGREE_LOW = 1;
    private static final Character IMPORTANCE_DEGREE_NORMAL = 2;
    private static final Character IMPORTANCE_DEGREE_HIGH = 3;
    private String username;
    private String remark;
    private BmobDate endTime;
    private BmobDate deadline;
    private Character urgencyDegree;
    private Character importanceDegree;
    private Character state;
    private String failReason;

    public Delay() {
    }

    public Delay(String username, BmobDate deadline, Character urgencyDegree, Character importanceDegree) {
        this.username = username;
        this.deadline = deadline;
        this.urgencyDegree = urgencyDegree;
        this.importanceDegree = importanceDegree;
    }

    public Delay(String username, String remark, BmobDate deadline, Character urgencyDegree, Character importanceDegree) {
        this.username = username;
        this.remark = remark;
        this.deadline = deadline;
        this.urgencyDegree = urgencyDegree;
        this.importanceDegree = importanceDegree;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BmobDate getEndTime() {
        return endTime;
    }

    public void setEndTime(BmobDate endTime) {
        this.endTime = endTime;
    }

    public BmobDate getDeadline() {
        return deadline;
    }

    public void setDeadline(BmobDate deadline) {
        this.deadline = deadline;
    }

    public Character getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(Character urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public Character getImportanceDegree() {
        return importanceDegree;
    }

    public void setImportanceDegree(Character importanceDegree) {
        this.importanceDegree = importanceDegree;
    }

    public Character getState() {
        return state;
    }

    public void setState(Character state) {
        this.state = state;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
