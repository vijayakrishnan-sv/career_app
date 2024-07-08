package com.csym030.careerportal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppliedJobsPage extends AppCompatActivity {
    public static boolean appliedJobFlag = false;
    DatabaseController databaseController;
    JobsAdminView jobView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_jobs);

        //Initialisation value to the view
        TextView txt_vew = (TextView) findViewById(R.id.heading);
        recyclerView = findViewById(R.id.recyclerview);
        txt_vew.setText("Jobs Applied");

        //Initialise SQLite
        databaseController = new DatabaseController(this);

        //get all jobs
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("user_name");
        appliedJobFlag = true;
        Cursor cursor = databaseController.getAllAppliedJobs(user_name);

        int i = 0;
        ArrayList<JobsDao> jobList = new ArrayList<>();
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                i++;
                String name = cursor.getString(1);
                String org = cursor.getString(2);
                String addr = cursor.getString(3);
                String phn = cursor.getString(4);

                JobsDao job = new JobsDao(name,org,addr,phn);
                jobList.add(job);

            }
            //Listing Jobs in Recycler View
            jobView = new JobsAdminView(AppliedJobsPage.this, jobList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AppliedJobsPage.this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(jobView);
        }

    }


}