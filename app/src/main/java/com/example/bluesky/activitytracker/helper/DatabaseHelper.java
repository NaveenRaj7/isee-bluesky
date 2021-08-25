package com.example.bluesky.activitytracker.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bluesky.activitytracker.model.Activities;
import com.example.bluesky.activitytracker.model.Categories;
import com.example.bluesky.activitytracker.model.Password;
import com.example.bluesky.activitytracker.model.Project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by rajna on 5/4/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "activityManager";

    // Table Names
    private static final String TABLE_ACTIVITY = "activities";
    private static final String TABLE_CATEGORY = "categories";
    private static final String TABLE_PROJECT = "projects";
    private static final String TABLE_PASSWORD = "password";
    private static final String TABLE_ACTIVITY_CATEGORY = "activities_categories";


    // Common column names
            public static final String KEY_ID = "_id";
        public static final String KEY_ID_ACT = "_idp";
   // public static final String KEY_ID_CAT = "_id";
   // public static final String KEY_ID_PROJ = "_id";
    //private static final String KEY_CREATED_AT = "created_at";


    // ACTIVITY Table - column names
    public static final String KEY_TITLE = "title";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_PROJECT = "project";
    public static final String KEY_NOTES = "notes";

    // CATEGORY Table - column names
    public static final String KEY_CAT_NAME = "cat_name";

    // PROJECT Table - column names
    public static final String KEY_PROJ_NAME = "proj_name";

    // PASSWORD Table - column names
    public static final String KEY_PASS = "pass";

    // ACTIVITY_CATEGORY Table - column names
    private static final String KEY_ACTIVITY_ID = "activity_id";
    private static final String KEY_CATEGORY_ID = "category_id";
    private static final String KEY_PROJECT_ID = "project_id";


    // Table Create Statements
    // ACTIVITY table create statement
    private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE "
            + TABLE_ACTIVITY + "(" + KEY_ID_ACT + " INTEGER PRIMARY KEY," + KEY_TITLE
            + " TEXT," + KEY_DATE
            + " TEXT," + KEY_TIME
            + " TEXT," + KEY_NOTES
            + " TEXT,"+ KEY_CATEGORY
            + " INTEGER," + KEY_PROJECT + " INTEGER," + " FOREIGN KEY(" + KEY_CATEGORY + ") REFERENCES " + TABLE_CATEGORY + "(" + KEY_ID + ")," + " FOREIGN KEY(" + KEY_PROJECT + ") REFERENCES " + TABLE_PROJECT + "(" + KEY_ID + "))";


    // private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE activities(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, date TEXT, time TEXT, category INTEGER, project INTEGER, FOREIGN KEY(category) REFERENCES categories(_id), FOREIGN KEY(project) REFERENCES projects(_id))";


    // CATEGORY table create statement
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CAT_NAME + " TEXT" + ")";


    // PROJECT table create statement
    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE " + TABLE_PROJECT
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PROJ_NAME + " TEXT" + ")";

    // PROJECT table create statement
    private static final String CREATE_TABLE_PASSWORD = "CREATE TABLE " + TABLE_PASSWORD
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PASS + " TEXT" + ")";


    // ACTIVITY_CATEGORY table create statement
    private static final String CREATE_TABLE_ACTIVITY_CATEGORY = "CREATE TABLE "
            + TABLE_ACTIVITY_CATEGORY + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_ACTIVITY_ID + " INTEGER," + KEY_CATEGORY_ID + " INTEGER" + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_ACTIVITY);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_PROJECT);
        db.execSQL(CREATE_TABLE_PASSWORD);
        db.execSQL(CREATE_TABLE_ACTIVITY_CATEGORY);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSWORD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY_CATEGORY);

        // create new tables
        onCreate(db);
    }


    /*
 * Creating a activity
 */
    public long createActivity(Activities activity, long[] cat_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(KEY_ID, activity.getId());
        values.put(KEY_DATE, activity.getDate());
        values.put(KEY_TITLE, activity.getTitle());
        values.put(KEY_TIME, activity.getTime());
        values.put(KEY_CATEGORY, activity.getCategory());
        values.put(KEY_PROJECT, activity.getProject());
        values.put(KEY_NOTES, activity.getNotes());
        //values.put(KEY_DATE, getDateTime());


        // insert row
        long activity_id = db.insert(TABLE_ACTIVITY, null, values);
        System.out.println(activity_id);

        /*if(activity_id>2) {
            db.delete(TABLE_ACTIVITY, KEY_ID + " = ?",
                    new String[]{String.valueOf(activity_id)});
        }*/

        // assigning tags to todo
        for (long tag_id : cat_ids) {
            createActivityCat(activity_id, tag_id);
        }

        return activity_id;
    }


    /*
 * get single todo
 */
    public Activities getActivity(long activity_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY + " WHERE "
                + KEY_ID_ACT + " = " + activity_id;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Activities ac = new Activities();
        ac.setId(c.getInt(c.getColumnIndex(KEY_ID_ACT)));
        ac.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
        ac.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
        ac.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
        ac.setCategory(c.getInt(c.getColumnIndex(KEY_CATEGORY)));
        ac.setProject(c.getInt(c.getColumnIndex(KEY_PROJECT)));
        ac.setNotes(c.getString(c.getColumnIndex(KEY_NOTES)));

        return ac;
    }


    /*
 * getting all todos
 * */
    public List<Activities> getAllActivities() {
        List<Activities> activities = new ArrayList<Activities>();
        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Activities ac = new Activities();
                ac.setId(c.getInt((c.getColumnIndex(KEY_ID_ACT))));
                ac.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                ac.setDate(c.getString(c.getColumnIndex(KEY_DATE)));
                ac.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
                ac.setCategory(c.getInt(c.getColumnIndex(KEY_CATEGORY)));
                ac.setProject(c.getInt(c.getColumnIndex(KEY_PROJECT)));
                ac.setNotes(c.getString(c.getColumnIndex(KEY_NOTES)));

                // adding to todo list
                activities.add(ac);
            } while (c.moveToNext());
        }

        return activities;
    }




   /* public Cursor fetchAllActivities() {

        Cursor mCursor = mDb.query(SQLITE_TABLE, new String[] {KEY_ROWID,
                        KEY_CODE, KEY_NAME, KEY_CONTINENT, KEY_REGION},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


*/


    public Cursor fetchData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT  * FROM " + TABLE_ACTIVITY + " INNER JOIN " + TABLE_CATEGORY + " ON " + TABLE_ACTIVITY +
                "." + KEY_CATEGORY + " = " + TABLE_CATEGORY + "." + KEY_ID
                + " INNER JOIN " + TABLE_PROJECT + " ON " + TABLE_ACTIVITY +
                "." + KEY_PROJECT + " = " + TABLE_PROJECT + "." + KEY_ID + " ORDER BY date(" + KEY_DATE + ") DESC, time(substr(" + KEY_TIME + ",1,5)) DESC";
        return db.rawQuery(str, null);

    }



    /*public Cursor getActivities_filtered(String f_date, String t_date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT  * FROM " + TABLE_ACTIVITY + " INNER JOIN " + TABLE_CATEGORY + " ON " + TABLE_ACTIVITY +
                "." + KEY_CATEGORY + " = " + TABLE_CATEGORY + "." + KEY_ID
                + " INNER JOIN " + TABLE_PROJECT + " ON " + TABLE_ACTIVITY +
                "." + KEY_PROJECT + " = " + TABLE_PROJECT + "." + KEY_ID + " WHERE date("+ KEY_DATE + ") BETWEEN date("+
                f_date + ") AND date(" + t_date + ") ORDER BY date(" + KEY_DATE + ") DESC, time(substr(" + KEY_TIME + ",1,5)) DESC";
        System.out.println(str);
        return db.rawQuery(str, null);

    }*/


    public Cursor getActivities_filtered(String f_date, String t_date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT  * FROM " + TABLE_ACTIVITY + " INNER JOIN " + TABLE_CATEGORY + " ON " + TABLE_ACTIVITY +
                "." + KEY_CATEGORY + " = " + TABLE_CATEGORY + "." + KEY_ID
                + " INNER JOIN " + TABLE_PROJECT + " ON " + TABLE_ACTIVITY +
                "." + KEY_PROJECT + " = " + TABLE_PROJECT + "." + KEY_ID + " WHERE " + KEY_DATE + " >= '"+
                f_date + "' AND date <= '" +  t_date + "' ORDER BY date(" + KEY_DATE + ") DESC, time(substr(" + KEY_TIME + ",1,5)) DESC";

        //String str = "SELECT  * FROM activities INNER JOIN categories ON activities.category = categories._id INNER JOIN projects ON " +
        //        "activities.project = projects._id WHERE date >= '" +  f_date + "' AND date <= '" +  t_date + "' ORDER BY date(date) DESC, time(substr(time,1,5)) DESC";
        System.out.println(str);
        return db.rawQuery(str, null);

    }


//SELECT  * FROM activities INNER JOIN categories ON activities.category = categories._id INNER JOIN projects ON activities.project = projects._id WHERE activities.date = 2017-06-24 ORDER BY date(date) DESC, time(substr(time,1,5)) DESC







    /*
 * getting all todos under single tag
 * */
    //P
    public List<Activities> getAllActivitiesByCat(String cat_name) {
        List<Activities> activities = new ArrayList<Activities>();

        String selectQuery = "SELECT  * FROM " + TABLE_ACTIVITY + " td, "
                + TABLE_CATEGORY + " tg, " + TABLE_ACTIVITY_CATEGORY + " tt WHERE tg."
                + KEY_CAT_NAME + " = '" + cat_name + "'" + " AND tg." + KEY_ID
                + " = " + "tt." + KEY_CATEGORY_ID + " AND td." + KEY_ID + " = "
                + "tt." + KEY_ACTIVITY_ID;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Activities td = new Activities();
                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                td.setTitle((c.getString(c.getColumnIndex(KEY_TITLE))));
                td.setDate(c.getString(c.getColumnIndex(KEY_DATE)));

                // adding to todo list
                activities.add(td);
            } while (c.moveToNext());
        }
        return activities;

    }


    /*
 * Updating a todo
 */
    public int updateActivity(Activities activities) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, activities.getTitle());


        // updating row
        return db.update(TABLE_ACTIVITY, values, KEY_ID_ACT + " = ?",
                new String[]{String.valueOf(activities.getId())});
    }


    /*
 * Deleting a todo
 */

    public String show_notes(long activity_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String str = "SELECT " + KEY_NOTES + " FROM " + TABLE_ACTIVITY + " WHERE " + KEY_ID_ACT + " = " + activity_id;

        Cursor c = db.rawQuery(str, null);
        String str_note = "Nil";

        //int i = 0;
        if (c.moveToFirst()) {
            do {
                str_note = c.getString(c.getColumnIndex(KEY_NOTES));
                //
                // t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                //td.setNotes(c.getString(c.getColumnIndex(KEY_NOTES)));

                /*if (i == 3) {
                    // adding to tags list
                    str_note = td.getNotes();
                }*/


            } while (c.moveToNext());
        }

        return str_note;
    }


    public int getPass() {

        SQLiteDatabase db = this.getWritableDatabase();
        String str = "SELECT " + KEY_PASS + " FROM " + TABLE_PASSWORD;

        Cursor c = db.rawQuery(str, null);
        String str_note = "Nil";

        int i = 0;
        if (c.moveToFirst()) {
            do {
                i = c.getInt(c.getColumnIndex(KEY_PASS));

                //
                // t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                //td.setNotes(c.getString(c.getColumnIndex(KEY_NOTES)));

                /*if (i == 3) {
                    // adding to tags list
                    str_note = td.getNotes();
                }*/


            } while (c.moveToNext());
        }

        return i;
    }




    public int updatePassword(int new_p) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASS, new_p);


        // updating row
        return db.update(TABLE_PASSWORD, values, KEY_ID + " = 1", null);
    }






    public void deleteActivity(long activity_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITY, KEY_ID_ACT + " = ?",
                new String[]{String.valueOf(activity_id)});
    }


    /*
 * Creating tag
 */
    public long createTag(Categories cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NAME, cat.getCat_name());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long cat_id = db.insert(TABLE_CATEGORY, null, values);

        return cat_id;
    }


    /**
     * getting all tags
     */
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        //List<Categories> categories1 = new ArrayList<Categories>();
        String selectQuery = "SELECT " + KEY_CAT_NAME + " FROM " + TABLE_CATEGORY;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Categories t = new Categories();
                // t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setCat_name(c.getString(c.getColumnIndex(KEY_CAT_NAME)));

                // adding to tags list
                categories.add(t.getCat_name());

            } while (c.moveToNext());
        }
        return categories;
    }

    public Cursor getCategories_All() {
        SQLiteDatabase db = this.getReadableDatabase();
        String str = "SELECT  * FROM " + TABLE_CATEGORY;
        return db.rawQuery(str, null);

    }






    public long createProj(Project proj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROJ_NAME, proj.getproj_name());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long proj_id = db.insert(TABLE_PROJECT, null, values);

        return proj_id;
    }



    //Set Password
    public long setPass(Password pa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASS, pa.getPassword());
        //values.put(KEY_CREATED_AT, getDateTime());

        // insert row
        long proj_id = db.insert(TABLE_PASSWORD, null, values);

        return proj_id;
    }







    /**
     * getting all projects
     */
    public List<String> getAllProjs() {
        List<String> projects = new ArrayList<String>();
        //List<Categories> categories1 = new ArrayList<Categories>();
        String selectQuery = "SELECT " + KEY_PROJ_NAME + " FROM " + TABLE_PROJECT;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Project t = new Project();
                // t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setproj_name(c.getString(c.getColumnIndex(KEY_PROJ_NAME)));

                // adding to tags list
                projects.add(t.getproj_name());

            } while (c.moveToNext());
        }
        return projects;
    }






































    /*public String getCategory(int id_c) {
        //List<String> categories = new ArrayList<String>();
        //List<Categories> categories1 = new ArrayList<Categories>();
        String selectQuery = "SELECT " + KEY_CAT_NAME + " FROM " + TABLE_CATEGORY + " WHERE " + KEY_ID + " = " + id_c;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Categories t = new Categories();
                // t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setCat_name(c.getString(c.getColumnIndex(KEY_CAT_NAME)));

                // adding to tags list
                categories.add(t.getCat_name());

            } while (c.moveToNext());
        }
        return categories;
    }*/


    /*
 * Updating a tag
 */
    public int updateCategories(Categories cat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CAT_NAME, cat.getCat_name());

        // updating row
        return db.update(TABLE_CATEGORY, values, KEY_ID + " = ?",
                new String[]{String.valueOf(cat.getId())});
    }


    /*
 * Deleting a tag
 */
    public void deleteCategory(Categories cat, boolean should_delete_all_cat_activities) {
        SQLiteDatabase db = this.getWritableDatabase();

        // before deleting tag
        // check if todos under this tag should also be deleted
        if (should_delete_all_cat_activities) {
            // get all todos under this tag
            List<Activities> allCatActivities = getAllActivitiesByCat(cat.getCat_name());

            // delete all todos
            for (Activities ac : allCatActivities) {
                // delete todo
                deleteActivity(ac.getId());
            }
        }

        // now delete the tag
        db.delete(TABLE_CATEGORY, KEY_ID + " = ?",
                new String[]{String.valueOf(cat.getId())});
    }


    /*
     * Creating todo_tag
     */
    public long createActivityCat(long act_id, long cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITY_ID, act_id);
        values.put(KEY_CATEGORY_ID, cat_id);
        //values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_ACTIVITY_CATEGORY, null, values);

        return id;
    }


    /*
 * Updating a todo tag
 */
    public int updateActivityCat(long id, long cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CATEGORY_ID, cat_id);

        // updating row
        return db.update(TABLE_ACTIVITY, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


    /**
     * get datetime
     */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


}






