package com.example.bluesky.activitytracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Categories;
import com.example.bluesky.activitytracker.model.Project;

public class CreateProject extends Activity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        db = new DatabaseHelper(getApplicationContext());

    }


    public void addProj(View view) {

        EditText new_proj_name = (EditText) findViewById(R.id.proj_name);
        Project new_proj = new Project(new_proj_name.getText().toString());

        long new_proj_id = db.createProj(new_proj);

        db.closeDB();
        //Intent intent = new Intent(CreateProject.this, CreateActivity.class);
        //startActivity(intent);
        this.finish();

    }
}
