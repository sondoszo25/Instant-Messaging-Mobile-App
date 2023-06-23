package com.example.mtkdem2_targel3;

public class foraddchat {
    private int id;
    private MyProfile user;

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

    public foraddchat(int id, MyProfile user) {
        this.id = id;
        this.user = user;
    }
}
