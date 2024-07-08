package com.csym030.careerportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        //Variable Declaration and initialisation
        Button job_add_button = findViewById(R.id.job_add_button);
        Button job_show_button = findViewById(R.id.all_job_button);

        //Add Jobs button action
        job_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Launch new Activity to Add Job
                Intent intnt = new Intent(AdminPage.this, NewJob.class);
                startActivity(intnt);
            }
        });

        //View Jobs button action
        job_show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Launch new Activity to View Jobs
                Intent i = new Intent(AdminPage.this, AdminAllJobs.class);
                startActivity(i);
            }
        });
    }
}