package com.csym030.careerportal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewJob extends AppCompatActivity {
    DatabaseController dbController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_job);

        //Variable Declaration
        EditText title = (EditText) findViewById(R.id.newjob_title);
        EditText phone = (EditText) findViewById(R.id.newjob_phone);
        EditText org = (EditText) findViewById(R.id.newjob_org);
        EditText addr = (EditText) findViewById(R.id.newjob_address);
        Button add_button = findViewById(R.id.Add);

        //Initialise SQLite
        dbController = new DatabaseController(this);

        //Add button listener
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viw) {
                String name = title.getText().toString();
                String contactNumber = phone.getText().toString();
                String organisation = org.getText().toString();
                String address = addr.getText().toString();

                //validation
                if(name.isEmpty() && contactNumber.isEmpty() && organisation.isEmpty() && address.isEmpty()){
                    Toast.makeText(NewJob.this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Add Job
                JobsDao job = new JobsDao(name,address, contactNumber,organisation);
                dbController.addJob(job);
                Toast.makeText(NewJob.this, "Job added Successfully ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
