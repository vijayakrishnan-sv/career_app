package com.csym030.careerportal;

import static com.csym030.careerportal.AppliedJobsPage.appliedJobFlag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        //Declaration
        Button show_job_Btn = findViewById(R.id.show_job_Btn);
        Button applied_job_Btn = findViewById(R.id.applied_job_Btn);

        show_job_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appliedJobFlag = false;
                //Launch new Activity
                Intent i = new Intent(UserPage.this, UserJobs.class);
                startActivity(i);
            }
        });
        applied_job_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String user_name = intent.getStringExtra("user_name");
                Intent i = new Intent(UserPage.this, AppliedJobsPage.class);
                i.putExtra("user_name", user_name);
                startActivity(i);
            }
        });
    }
}