package com.example.mtkdem2_targel3;

public class usernamesingelton {
    private String username;
    private static usernamesingelton instance;

    private usernamesingelton() {
    }

    public static usernamesingelton getInstance() {
        if (instance == null) {
            instance = new usernamesingelton();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
