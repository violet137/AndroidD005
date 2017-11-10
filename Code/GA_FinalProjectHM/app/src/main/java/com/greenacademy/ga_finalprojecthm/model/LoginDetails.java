package com.greenacademy.ga_finalprojecthm.model;

/**
 * Created by thepa on 11/9/2017.
 */

public class LoginDetails {
    private String token;
    private String description;
    private int status;

    public void setToken(String token) {
        this.token = token;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }
}
