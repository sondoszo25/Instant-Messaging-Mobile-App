package com.example.mtkdem2_targel3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MyProfile {
    @PrimaryKey(autoGenerate=true)
    private int id;
    private String username;
    private String password;
    private String displayName;
    private String profilePic;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public MyProfile(String username, String password, String displayName, String profilePic) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
