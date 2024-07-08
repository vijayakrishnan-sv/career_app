package com.csym030.careerportal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DatabaseController extends SQLiteOpenHelper {

    private int dbVersion = 1;
    private static String dbName = "careerPortal";
    private String users = "users";
    private String jobs = "jobs";
    private String jobsApplied = "jobs_applied";
    private String Id = "id";
    private String userName = "name";
    private String jobName = "name";
    private String emailId = "email";
    private String employer = "company";
    private String country = "country";
    private String address = "address";
    private String contactNo = "phoneNumber";
    private String password = "password";

    public DatabaseController(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create tables
        String jobs_query = "create table " + jobs + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                jobName + " TEXT," + employer + " TEXT," + address + " TEXT," + contactNo + " TEXT)";
        sqLiteDatabase.execSQL(jobs_query);

        String user_query = "create table " + users + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                userName + " TEXT," + country + " TEXT," +contactNo + " TEXT," +emailId + " TEXT," +password + " TEXT)";
        sqLiteDatabase.execSQL(user_query);

        String applied_query = "create table " + jobsApplied + "(" + Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                jobName + " TEXT," + employer + " TEXT," + address + " TEXT," + contactNo + " TEXT," +emailId + " TEXT)";
        sqLiteDatabase.execSQL(applied_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ users);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ jobs);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ jobsApplied);
        onCreate(sqLiteDatabase);
    }

    //Add job
    public void addJob(JobsDao job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(jobName, job.getTitle());
        values.put(employer,job.getEmployer() );
        values.put(address,job.getAddress() );
        values.put(contactNo,job.getContactNumber() );
        db.insert(jobs, null, values);
        db.close();
    }

    //Add user
    public void signUp(UserDao user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(userName, user.getName());
        values.put(contactNo, user.getContactNumber());
        values.put(country, user.getCountry());
        values.put(emailId, user.getEmailId());
        values.put(password, user.getPassword());
        db.insert(users, null, values);
        db.close();
    }

    //Apply job
    public void applyJob(AppliedJobsDao job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(jobName, job.getTitle());
        values.put(contactNo, job.getContactNumber());
        values.put(employer, job.getEmployer());
        values.put(address, job.getAddress());
        values.put(emailId, job.getEmailId());
        db.insert(jobsApplied, null, values);
        db.close();
    }
    //Delete job
    public boolean deleteJob(String job){
        SQLiteDatabase db = this.getWritableDatabase();
        int endResult  = db.delete(jobs, jobName + " =? ", new String[]{String.valueOf(job)});
        if(endResult > 0){
            return true;
        }
        else{
            return false;
        }
    }


    //All jobs
    public Cursor getAllJobs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] { Id, jobName, employer,address, contactNo };
        Cursor cursor = db.query(jobs, columns, null,
                null, null, null, null);
        return cursor;
    }

    //Applied jobs
    public Cursor getAllAppliedJobs(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "
                + jobsApplied + " WHERE " + emailId + "=?", new String[]{String.valueOf(email)});

        return c;
    }

    //User Details
    public int getUser(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM "
                + users + " WHERE " + emailId + "=? AND "+password+"=?", new String[]{""+email,""+pass});
        return c.getCount();
    }


}