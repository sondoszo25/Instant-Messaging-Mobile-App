package com.example.mtkdem2_targel3;

public class FriendProfile {
    private static FriendProfile instance;
    private String username;
    private int id;
    private String pic;

    private FriendProfile() {
    }

    public static FriendProfile getInstance() {
        if (instance == null) {
            instance = new FriendProfile();
        }
        return instance;
    }

    public static void setInstance(FriendProfile instance) {
        FriendProfile.instance = instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
