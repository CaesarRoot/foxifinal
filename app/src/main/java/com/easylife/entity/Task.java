package com.easylife.entity;

import cn.bmob.v3.BmobObject;

public class Task extends BmobObject{
    public static final int STATE_TODO = 0;
    public static final int STATE_FINISH = 1;
    public static final int STATE_FAILED = 2;
    private String username;
    private String todo;
    private String finished;
    private String failed;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }
}
