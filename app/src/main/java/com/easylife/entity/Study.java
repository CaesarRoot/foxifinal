package com.easylife.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class Study extends BmobObject {
    private String username;
    private Integer presetTime;
    private BmobDate endTime;
    private Boolean isSuccess;
    private String remark;
    private String failReason;

    public Study(){};

    public Study(String username, Integer presetTime) {
        this.username = username;
        this.presetTime = presetTime;
    }

    public Study(String username, Integer presetTime,String remark) {
        this.remark = remark;
        this.username = username;
        this.presetTime = presetTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPresetTime() {
        return presetTime;
    }

    public void setPresetTime(Integer presetTime) {
        this.presetTime = presetTime;
    }

    public BmobDate getEndTime() {
        return endTime;
    }

    public void setEndTime(BmobDate endTime) {
        this.endTime = endTime;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
