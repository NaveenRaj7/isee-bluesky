package com.example.bluesky.activitytracker.model;

/**
 * Created by rajna on 5/19/2017.
 */

public class Project {

    int id;
    String proj_name;

    // constructors
    public Project() {

    }

    public Project(String proj_name) {
        this.proj_name = proj_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setproj_name(String proj_name) {
        this.proj_name = proj_name;
    }


    // getter
    public int getId() {
        return this.id;
    }

    public String getproj_name() {
        return this.proj_name;
    }



}
