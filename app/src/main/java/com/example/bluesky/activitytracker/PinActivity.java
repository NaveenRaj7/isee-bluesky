package com.example.bluesky.activitytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Password;

import java.util.List;

public class PinActivity extends AppCompatActivity {
    EditText pass;

    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        db = new DatabaseHelper(getApplicationContext());

        List<String> allCategories = db.getAllCategories();
        if(allCategories.isEmpty()) {
            //set default password
            Password p = new Password(0000);
            long p_id = db.setPass(p);
        }

        pass = (EditText)findViewById(R.id.password);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void submit(View v){

        if(pass.getText().toString().isEmpty()) {
            Toast.makeText(PinActivity.this, "Please enter correct password",
                    Toast.LENGTH_SHORT).show();
        }

        else{
        if (Integer.parseInt(pass.getText().toString()) == db.getPass()) {

            Intent intent = new Intent(PinActivity.this, MainActivity.class);


            startActivity(intent);
            this.finish();
        }
        else {
            Toast.makeText(PinActivity.this, "Please enter correct password",
                    Toast.LENGTH_SHORT).show();
        }
    }

    }
}
