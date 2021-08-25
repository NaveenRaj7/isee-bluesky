package com.example.bluesky.activitytracker;


import com.example.bluesky.activitytracker.helper.DatabaseHelper;
import com.example.bluesky.activitytracker.model.Activities;
import com.example.bluesky.activitytracker.model.Categories;
import com.example.bluesky.activitytracker.model.Password;
import com.example.bluesky.activitytracker.model.Project;
import com.example.bluesky.activitytracker.pickers.Filtered_Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    // Database Helper
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        /*NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        db = new DatabaseHelper(getApplicationContext());

        List<String> allCategories = db.getAllCategories();
        if(allCategories.isEmpty()) {

            // Creating categories
            Categories cat0 = new Categories("<Select Category>");
            Categories cat1 = new Categories("------Add Category------");
            Categories cat2 = new Categories("Sports");
            Categories cat3 = new Categories("Entertainment");
            Categories cat4 = new Categories("Food");
            Categories cat5 = new Categories("Study");




            // Inserting categories in db
            long cat0_id = db.createTag(cat0);
            long cat1_id = db.createTag(cat1);
            long cat2_id = db.createTag(cat2);
            long cat3_id = db.createTag(cat3);
            long cat4_id = db.createTag(cat4);
            long cat5_id = db.createTag(cat5);


            Project proj0 = new Project("Default Project");
            Project proj1 = new Project("------Add Project------");

            long proj0_id = db.createProj(proj0);
            long proj1_id = db.createProj(proj1);




        }
        // Creating Activities
        /*Activities act1 = new Activities("Lecture");
        Activities act2 = new Activities("Football");
        Activities act3 = new Activities("Movie");
        Activities act4 = new Activities("Mensa");
        Activities act5 = new Activities("Workshop");
        Activities act6 = new Activities("Dinner");
        Activities act7 = new Activities("Jogging");
        Activities act8 = new Activities("Cycling");
        Activities act9 = new Activities("Meeting");




        // Inserting Activities in db
        // Inserting Activities under "Study" Tag
        long act1_id = db.createActivity(act1, new long[] { cat1_id });
        long act9_id = db.createActivity(act9, new long[] { cat1_id });
        long act5_id = db.createActivity(act5, new long[] { cat1_id });

        // Inserting Activities under "Sports" Tag
        long act2_id = db.createActivity(act2, new long[] { cat2_id });
        long act7_id = db.createActivity(act7, new long[] { cat2_id });
        long act8_id = db.createActivity(act8, new long[] { cat2_id });

        // Inserting Activities under "Entertainment" Tag
        long act3_id = db.createActivity(act3, new long[] { cat3_id });

        // Inserting Activities under "Food" Tag
        long act4_id = db.createActivity(act4, new long[] { cat4_id });
        long act6_id = db.createActivity(act6, new long[] { cat4_id });*/






        // Getting all tag names
        // Log.d("Get Tags", "Getting All Tags");
        //List<String> allCategories = db.getAllCategories();




        // Getting all Todos
        //Log.d("Get Todos", "Getting All ToDos");
//        List<Activities> allActivities = db.getAllActivities();
    //    for (Activities act : allActivities) {
            //Log.d("ToDo", todo.getNote());
    //    }




        // Getting activities under "Study" tag name
        // Log.d("ToDo", "Get todos under single Tag name");
        //List<Activities> tagsStudy = db.getAllActivitiesByCat(cat1.getCat_name());




        // Deleting a ToDo
        // Log.d("Delete ToDo", "Deleting a Todo");
        // Log.d("Tag Count", "Tag Count Before Deleting: " + db.getToDoCount());

       // db.deleteActivity(act8_id);
        // Log.d("Tag Count", "Tag Count After Deleting: " + db.getToDoCount());


        // Deleting all Todos under "Shopping" tag
        // Log.d("Tag Count", "Tag Count Before Deleting 'Shopping' Todos: " + db.getToDoCount());

        //db.deleteCategory(cat1, true);

        // Log.d("Tag Count", "Tag Count After Deleting 'Shopping' Todos: " + db.getToDoCount());



        // Updating tag name
        //cat3.setCat_name("Entertainment");
        //db.updateCategories(cat3);




        /*Context context;
        Cursor cursor = getContentResolver().query(activityManager.CONTENT_URI, new String[] {activityManager._ID, activityManager.NAME, activityManager.NUMBER}, null, null, null);
        startManagingCursor(cursor);



        SQLiteCursor cu = new SQLiteCursor(SQLiteCursorDriver driver,  )
        Uri.fromFile(context.getDatabasePath("activityManager.db"))*/





        final ListView alist = (ListView) findViewById(R.id.activity_list);


        Cursor cursor = db.fetchData();
       // startManagingCursor(cursor);


        // THE DESIRED COLUMNS TO BE BOUND
        String[] columns = new String[] {db.KEY_TITLE, db.KEY_DATE, db.KEY_TIME, db.KEY_CAT_NAME, db.KEY_ID_ACT};

        // THE XML DEFINED VIEWS WHICH THE DATA WILL BE BOUND TO
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

        //mAdapter.getView()



        // SET THIS ADAPTER AS YOUR LIST ACTIVITY'S ADAPTER
        alist.setAdapter(mAdapter);






        //Deleted individual cart items
        //on list view cell long press
        alist.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            @SuppressWarnings("rawtypes")
            public boolean onItemLongClick(final AdapterView parent, final View view, final int position, final long id) {
                final CharSequence[] items = { "Delete?", "Show Notes" };
                final Context context = view.getContext();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Action:");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        //cart = cartList.get(position);


                        if(item == 0) {
                            new AlertDialog.Builder(context)
                                    .setTitle("Delete Activity")
                                    .setMessage("Are you sure?")
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            db.deleteActivity(Long.parseLong(((TextView) view.findViewById(R.id.id_entry)).getText().toString()));
                                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        }

                        if(item == 1) {
                            Intent intent = new Intent(MainActivity.this, ShowNotes.class);
                            Bundle bundle = new Bundle();

                            bundle.putLong("id", Long.parseLong(((TextView) view.findViewById(R.id.id_entry)).getText().toString()));
                            //bundle.putString("to", to_field);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    }

                });

                AlertDialog alert = builder.create();

                alert.show();
                //do your stuff here
                return false;
            }
        });


        // close database connection
        db.closeDB();

    }






    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, Settings.class);
            startActivity(intent);
        }

        if (id == R.id.create_category) {
            Intent intent = new Intent(MainActivity.this, CreateCategory.class);
            startActivity(intent);
        }

        if (id == R.id.create_project) {
            Intent intent = new Intent(MainActivity.this, CreateProject.class);
            startActivity(intent);
        }

        if (id == R.id.filter_activities) {
            Intent intent = new Intent(MainActivity.this, Filter.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

   /* @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/


    public void newActivity(View view) {
        // Do something in response to button click
        //setContentView(R.layout.activity_create);
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);

        // TextView tView = (TextView) findViewById(R.id.textView);
        // tView.setText("Hey!");
    }
}
