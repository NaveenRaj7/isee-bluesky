package com.example.bluesky.activitytracker;

import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Activities;
import com.example.bluesky.activitytracker.pickers.Filtered_Activities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Filter extends AppCompatActivity {

    DatabaseHelper db;
    ArrayAdapter<String> dataAdapter;
    private TextView from_date, to_date;
    private int mYear, mMonth, mDay;
    Button from_DatePicker, to_DatePicker;
    private String from_field, to_field, fil_query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();}});



        db = new DatabaseHelper(getApplicationContext());
        final ListView fil_cat_list = (ListView) findViewById(R.id.filter_cat_list);

        from_DatePicker=(Button)findViewById(R.id.fil_from_button);
        to_DatePicker=(Button)findViewById(R.id.fil_to_button);

        from_date = (TextView) findViewById(R.id.filter_from);
        to_date = (TextView) findViewById(R.id.filter_to);


        List<String> allCategories = db.getAllCategories();
        allCategories.set(0, "All");
        allCategories.remove(1);
        dataAdapter = new ArrayAdapter<String>(this, R.layout.filter_cat_listview, R.id.fil_cat_name, allCategories);
        fil_cat_list.setAdapter(dataAdapter);


        final CheckBox tv = (CheckBox) findViewById(R.id.fil_checkbox);

        /*final ArrayList<String> selectedStrings = new ArrayList<String>();

        tv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedStrings.add(tv.getText().toString());
                    System.out.println(tv.getText().toString());
                }else{
                    selectedStrings.remove(tv.getText().toString());
                }

            }
        });*/





        /*Cursor cursor = db.getCategories_All();

        String[] columns = new String[] {db.KEY_CAT_NAME};

        int[] to = new int[] {R.id.fil_cat_name};

        ListAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.filter_cat_listview, cursor, columns, to, 0);

        fil_cat_list.setAdapter(mAdapter);*/



        /*Button fil_btn = (Button) findViewById(R.id.fil_filter_button);


        fil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = getLayoutInflater().inflate(R.layout.activity_main, null);
                //setContentView(R.layout.activity_main);

            }
        });*/


    }



    public void showDatePickerDialog(final View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        DecimalFormat mFormat= new DecimalFormat("00");
                        //mFormat.format(Double.valueOf(year));

                        if(v == from_DatePicker) {
                            from_date.setText(year + "-" + (mFormat.format(Double.valueOf(monthOfYear + 1))) + "-" + mFormat.format(Double.valueOf(dayOfMonth)));
                            from_field = from_date.getText().toString();
                        }

                        if(v == to_DatePicker) {
                            to_date.setText(year + "-" + (mFormat.format(Double.valueOf(monthOfYear + 1))) + "-" + mFormat.format(Double.valueOf(dayOfMonth)));
                            to_field = to_date.getText().toString();
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }





    public void filter_activities(View view) {

        //Cursor cursor = db.getActivities_filtered(from_field, to_field);
        //setContentView(R.layout.activity_main);

        if(from_field == null || to_field == null){



        }

        else {
            Intent intent = new Intent(Filter.this, Filtered_Activities.class);
            Bundle bundle = new Bundle();

            bundle.putString("from", from_field);
            bundle.putString("to", to_field);
            intent.putExtras(bundle);
            startActivity(intent);
            this.finish();
        }
        //View v = getLayoutInflater().inflate(R.layout.activity_main, null);
        //final ListView alist = (ListView)v.findViewById(R.id.activity_list);
        // startManagingCursor(cursor);
    }

}
