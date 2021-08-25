package com.example.bluesky.activitytracker.pickers;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.bluesky.activitytracker.R;
import com.example.bluesky.activitytracker.helper.DatabaseHelper;

public class Filtered_Activities extends AppCompatActivity {

    DatabaseHelper db;
    String from_field, to_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered__activities);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        from_field = bundle.getString("from");
        to_field = bundle.getString("to");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        db = new DatabaseHelper(getApplicationContext());

        final ListView alist = (ListView)findViewById(R.id.activity_list2);
        Cursor cursor = db.getActivities_filtered(from_field, to_field);
        //Cursor cursor = db.fetchData();
        String[] columns = new String[] {db.KEY_TITLE, db.KEY_DATE, db.KEY_TIME, db.KEY_CAT_NAME, db.KEY_ID_ACT};

        int[] to = new int[] {R.id.title_entry, R.id.date_entry, R.id.time_entry, R.id.cat_entry, R.id.id_entry};

        final int[] colors = new int[] { 0xAA1A4C80, 0xAA5F82A6 };
        ListAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.activity_listview, cursor, columns, to, 0){

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                //TextView text = (TextView) view.findViewById(R.id.proj_entry);
                int colorPos = position % colors.length;
                //  if(text.getText() == "Default Proj.0ect"){
                view.setBackgroundColor(colors[colorPos]);
                //  }
                //text.setTextColor(Color.WHITE);
                //int colorPos = position % colors.length;
                //text.setBackgroundColor(colors[colorPos]);

                return view;
            }

        };

        alist.setAdapter(mAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, to_field + from_field, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
