package com.example.bluesky.activitytracker.model;

/**
 * Created by rajna on 5/4/2017.
 */

public class Activities {
    private int id, category, project;
    private String title, date, time, notes;
    // constructors
    public Activities() {
    }

    public Activities(String title, String date, String time, int category, int project, String notes) {
        this.date = date;
        this.title = title;
        this.time = time;
        this.category = category;
        this.project = project;
        this.notes = notes;
    }




    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date){
        this.date = date;
    }

    public void setTime(String time){
        this.time = time;
    }

    public void setCategory(int category){ this.category = category; }

    public void setProject(int project){ this.project = project; }

    public void setNotes(String notes) { this.notes = notes;}

    // getters
    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() { return this.time; }

    public int getCategory() { return this.category; }

    public int getProject() { return this.project; }

    public String getNotes(){ return this.notes; }

}
