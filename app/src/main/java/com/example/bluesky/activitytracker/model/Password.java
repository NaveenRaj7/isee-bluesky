package com.example.bluesky.activitytracker.model;

/**
 * Created by rajna on 7/4/2017.
 */

public class Password {

    private int id, password;

    public Password(int password) {
       // this.id = id;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public int getPassword() {
        return this.password;
    }


}
