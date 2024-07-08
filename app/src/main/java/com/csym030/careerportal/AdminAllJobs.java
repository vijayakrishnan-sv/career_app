package com.csym030.careerportal;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminAllJobs extends AppCompatActivity {

    List<JobsDao> jobs;
    DatabaseController dbController;
    JobsAdminView jobView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_jobs);

       //Initialisation value to the view
        TextView heading = (TextView) findViewById(R.id.heading);
        recyclerView = findViewById(R.id.recyclerview);
        heading.setText("Jobs List");

        //Initialise SQLite
        dbController = new DatabaseController(this);

        //get all jobs from the database
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
                String phone= cursor.getString(4);

                JobsDao job = new JobsDao(id,name,org,addr,phone);
                jobList.add(job);

            }
            //Listing Jobs in Recycler View
            jobView = new JobsAdminView(AdminAllJobs.this, jobList);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(AdminAllJobs.this, 1);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(jobView);
        }
    }
}