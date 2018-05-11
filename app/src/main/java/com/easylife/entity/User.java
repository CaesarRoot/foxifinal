package com.easylife.entity;

import org.json.JSONObject;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String nickname;
    private String pw;

    public User(){}

    public User(String mobilePhoneNumber, String password) {
        pw = password;
        this.setUsername("el"+mobilePhoneNumber);
        this.setPassword(password);
        this.setMobilePhoneNumber(mobilePhoneNumber);
        this.nickname = mobilePhoneNumber;
    }

    public User(String username, String password, String mobilePhoneNumber) {
        pw = password;
        this.setUsername(username);
        this.setPassword(password);
        this.setMobilePhoneNumber(mobilePhoneNumber);
        this.nickname = username;
    }

    public User(String username, String nickname, String password, String mobilePhoneNumber) {
        pw = password;
        this.setUsername(username);
        this.nickname = nickname;
        this.setPassword(password);
        this.setMobilePhoneNumber(mobilePhoneNumber);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return pw;
    }
}
