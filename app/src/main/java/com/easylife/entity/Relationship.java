package com.easylife.entity;

import cn.bmob.v3.BmobObject;

public class Relationship extends BmobObject {
    private String username;
    private String friendUserame;
    private String buddyNotes;

    public Relationship(){};

    public Relationship(String username, String friendUserame) {
        this.username = username;
        this.friendUserame = friendUserame;
        this.buddyNotes = friendUserame;
    }

    public Relationship( String username, String friendUserame, String buddyNotes) {
        this.username = username;
        this.friendUserame = friendUserame;
        this.buddyNotes = buddyNotes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendUserame() {
        return friendUserame;
    }

    public void setFriendUserame(String friendUserame) {
        this.friendUserame = friendUserame;
    }

    public String getBuddyNotes() {
        return buddyNotes;
    }

    public void setBuddyNotes(String buddyNotes) {
        this.buddyNotes = buddyNotes;
    }
}
