package com.example.bluesky.activitytracker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Activities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //private int year, month, day;
    private TextView dateView, startTimeView, endTimeView;
    private Spinner spinner, proj_spinner;
    private String Cat_field, Proj_field, date_field, title_field, time_field, startTime_field, endTime_field, notes_field;
    private int mYear, mMonth, mDay, mHour, mMinute;
    long cat1_id, proj_id;
    EditText et, notes;
    Button startBtnTimePicker, endBtnTimePicker;
    ArrayAdapter<String> dataAdapter, proj_dataAdapter;

    // Database Helper
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(getApplicationContext());
        et = (EditText) findViewById(R.id.et_title);
        notes = (EditText) findViewById(R.id.Add_Notes);

        startBtnTimePicker=(Button)findViewById(R.id.startTimeButton);
        endBtnTimePicker=(Button)findViewById(R.id.endTimeButton);

        startTimeView = (TextView) findViewById(R.id.textView4);
        endTimeView = (TextView) findViewById(R.id.textView5);


        spinner = (Spinner) findViewById(R.id.categories_spinner);
        proj_spinner = (Spinner) findViewById(R.id.projects_spinner);

        List<String> allCategories = db.getAllCategories();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allCategories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        List<String> allProjects = db.getAllProjs();
        proj_dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allProjects);
        proj_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proj_spinner.setAdapter(proj_dataAdapter);




        spinner.setOnItemSelectedListener(this);
        proj_spinner.setOnItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    public void submit(View view) {

        title_field = et.getText().toString();
        notes_field = notes.getText().toString();
        //time_field = et_time.getText().toString() + "Hrs";
        //time_field = timeDiff(startTime_field, endTime_field) + " Hrs";
        time_field = startTime_field + " - " + endTime_field;
        // Creating Activity
        if(title_field.isEmpty()||startTime_field==null||endTime_field==null||cat1_id==1||cat1_id==0) {
            Toast.makeText(CreateActivity.this, "Please fill all the details",
                    Toast.LENGTH_SHORT).show();
        }

        else {

            Activities act1 = new Activities(title_field, date_field, time_field, (int) cat1_id, (int) proj_id, notes_field);


            // Inserting Activity in db
            // Inserting Activity under corresponding Tag
            long act1_id = db.createActivity(act1, new long[]{cat1_id});

            Intent intent = new Intent(CreateActivity.this, MainActivity.class);
            startActivity(intent);
            this.finish();

        }

    }




    //metod not working
    public String timeDiff(String startTime, String endTime){
        String diff = "00:00";
        try
        {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            Date Date1 = format.parse(startTime);
            Date Date2 = format.parse(endTime);
            long mills = Date2.getTime() - Date1.getTime();

            int Hours = (int) (mills/(1000 * 60 * 60));
            int Mins = (int) (mills / (1000*60));

            diff = Hours + ":" + Mins;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return diff;
    }

    @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if(parent.getId() == R.id.categories_spinner) {
            // On selecting a spinner item
            if (position == 1) {

                Intent intent = new Intent(CreateActivity.this, CreateCategory.class);
                startActivity(intent);

            } else {
                Cat_field = parent.getItemAtPosition(position).toString();
                cat1_id = position + 1;

            }
        }


        if(parent.getId() == R.id.projects_spinner) {
            // On selecting a spinner item
            if (position == 1) {

                Intent intent = new Intent(CreateActivity.this, CreateProject.class);
                startActivity(intent);

            } else {
                Proj_field = parent.getItemAtPosition(position).toString();
                proj_id = position + 1;

            }
        }



    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void showDatePickerDialog(View v) {
        //DialogFragment newFragment = new DatePickerFragment();
        //newFragment.show(getSupportFragmentManager(), "datePicker");
        dateView = (TextView) findViewById(R.id.textView3);

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

                        dateView.setText(year + "-" + (mFormat.format(Double.valueOf(monthOfYear+1))) + "-" + mFormat.format(Double.valueOf(dayOfMonth)));
                        date_field = dateView.getText().toString();

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }




    public void showTimePickerDialog(View v) {

        if (v == startBtnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            DecimalFormat mFormat= new DecimalFormat("00");
                            //mFormat.format(Double.valueOf(year));

                            startTimeView.setText(mFormat.format(Double.valueOf(hourOfDay)) + ":" + mFormat.format(Double.valueOf(minute)));
                            startTime_field = startTimeView.getText().toString();
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }


        if (v == endBtnTimePicker) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            DecimalFormat mFormat= new DecimalFormat("00");
                            //mFormat.format(Double.valueOf(year));

                            endTimeView.setText(mFormat.format(Double.valueOf(hourOfDay)) + ":" + mFormat.format(Double.valueOf(minute)));
                            endTime_field = endTimeView.getText().toString();
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
        dataAdapter.notifyDataSetChanged();
    }
}