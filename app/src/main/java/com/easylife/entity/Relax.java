package com.easylife.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class Relax extends BmobObject {
    private static final Character TYPE_LIGHT_MUSIC = 0;
    private static final Character TYPE_WHITE_NOISE = 0;
    private String username;
    private Character type;
    private Integer presetTime;
    private BmobDate endTime;
    private Boolean isSuccess;

    public Relax() {
    }

    public Relax(String username, Character type, Integer presetTime) {
        this.username = username;
        this.type = type;
        this.presetTime = presetTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
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
}
