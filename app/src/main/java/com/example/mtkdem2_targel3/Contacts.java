package com.example.mtkdem2_targel3;

import androidx.room.Entity;

@Entity
public class Contacts {
    private int id;
    private MyProfile user;
    private Message lastMessage;

    public Contacts(int id, MyProfile user, Message lastMessage) {
        this.id = id;
        this.user = user;
        this.lastMessage = lastMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyProfile getUser() {
        return user;
    }

    public void setUser(MyProfile user) {
        this.user = user;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
