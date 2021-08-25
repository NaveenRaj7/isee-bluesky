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

public class ChangePassword extends AppCompatActivity {

    DatabaseHelper db;
    EditText old_p, new_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(getApplicationContext());

        old_p = (EditText)findViewById(R.id.old_password);
        new_p = (EditText) findViewById(R.id.new_password);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    public void submit(View v) {

        if (old_p.getText().toString().isEmpty() || new_p.getText().toString().isEmpty()) {
            Toast.makeText(ChangePassword.this, "Please enter 4 digit correct password",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (Integer.parseInt(old_p.getText().toString()) == db.getPass()) {

                db.updatePassword(Integer.parseInt(new_p.getText().toString()));

                Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                startActivity(intent);
                this.finish();

            } else {
                Toast.makeText(ChangePassword.this, "Please enter correct password",
                        Toast.LENGTH_SHORT).show();
            }

        }


    }
}
