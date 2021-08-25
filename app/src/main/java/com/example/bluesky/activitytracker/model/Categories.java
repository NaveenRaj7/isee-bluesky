package com.example.bluesky.activitytracker.model;

/**
 * Created by rajna on 5/4/2017.
 */

public class Categories {
    int id;
    String cat_name;

    // constructors
    public Categories() {


    }

    public Categories(String cat_name) {
        this.cat_name = cat_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }


    // getter
    public int getId() {
        return this.id;
    }

    public String getCat_name() {
        return this.cat_name;
    }


}
