package com.example.bluesky.activitytracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Categories;

public class CreateCategory extends Activity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        db = new DatabaseHelper(getApplicationContext());
    }


    public void addCat(View view) {

        EditText new_cat_name = (EditText) findViewById(R.id.cat_name);
        Categories new_cat = new Categories(new_cat_name.getText().toString());

        long new_cat_id = db.createTag(new_cat);

        db.closeDB();
            //Intent intent = new Intent(CreateCategory.this, CreateActivity.class);
            //startActivity(intent);
            this.finish();
            //startActivity(this.getParent().getIntent());
        }
    }
