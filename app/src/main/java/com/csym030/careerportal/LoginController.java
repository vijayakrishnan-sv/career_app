package com.csym030.careerportal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;


public class LoginController extends AppCompatActivity {

    public static String loginuser = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //Declaration and Initialisation
        EditText userName =  findViewById(R.id.user_name);
        EditText editPassword =  findViewById(R.id.password);
        TextView signupButton =  findViewById(R.id.signup_button);
        Button loginButton = findViewById(R.id.login_button);

        //SignUp button Listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Launch new Activity
                Intent i = new Intent(LoginController.this, SignUpController.class);
                startActivity(i);
            }
        });


        //Action to LoginController Button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usrname = userName.getText().toString();
                String pswd = editPassword.getText().toString();

                //Validation
                if(usrname.isEmpty() && pswd.isEmpty()){
                    Toast.makeText(LoginController.this, "All fields required", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Admin LoginController
                if(usrname.equals("admin") && pswd.equals("password"))
                {
                    Intent i = new Intent(LoginController.this, AdminPage.class);
                    loginuser = "admin" ;
                    startActivity(i);
                }
                //User LoginController
                else
                {
                    //Sqlite Helper initialisation
                    DatabaseController dbController = new DatabaseController(LoginController.this);
                    //Check User Exists
                    int cursor = dbController.getUser(usrname,pswd);
                    if(cursor>0)
                    {
                        //Launch new Activity
                        Intent i = new Intent(LoginController.this, UserPage.class);
                        i.putExtra("user_name", usrname);
                        loginuser = usrname;
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(LoginController.this, "No User Found", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }
}
