package com.csym030.careerportal;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserJobs extends AppCompatActivity {

    DatabaseController dbController;
    JobsAdminView jobView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_jobs);

        //Initialisation
        TextView tit = (TextView) findViewById(R.id.heading);
        recyclerView = findViewById(R.id.recyclerview);
        tit.setText("Jobs");

        //Initialise SQLite
        dbController = new DatabaseController(this);

        //get all jobs
        Cursor cursor = dbController.getAllJobs();

        int i = 0;
        ArrayList<JobsDao> jobList = new ArrayList<>();
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                i++;
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String org = cursor.getString(2);
                String addr = cursor.getString(3);
                String phn = cursor.getString(4);
                JobsDao job = new JobsDao(id, name,org,addr,phn);
                jobList.add(job);
            }
            jobView = new JobsAdminView(UserJobs.this, jobList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(UserJobs.this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(jobView);
        }

    }


}