package com.hsany.bio.server;

public class UserInfo {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfo(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
    }
}
