package com.csym030.careerportal;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpController extends AppCompatActivity {
    DatabaseController dbController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        //Declaration
        EditText new_name = (EditText) findViewById(R.id.signup_name);
        EditText new_number = (EditText) findViewById(R.id.signup_phone);
        EditText new_country = (EditText) findViewById(R.id.signup_country);
        EditText new_email = (EditText) findViewById(R.id.signup_email);
        EditText new_password = (EditText) findViewById(R.id.signup_password);
        Button register_buttton = findViewById(R.id.register);

        //Initialise SQLite
        dbController = new DatabaseController(this);

        //Register button Listener
        register_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = new_name.getText().toString();
                String phoneNo = new_number.getText().toString();
                String country = new_country.getText().toString();
                String email = new_email.getText().toString();
                String password = new_password.getText().toString();

                //Validation
                if(name.isEmpty() && phoneNo.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(SignUpController.this, "Please fill required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Add User
                UserDao user = new UserDao("",name,country, phoneNo,email,password);
                dbController.signUp(user);
                Toast.makeText(SignUpController.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
