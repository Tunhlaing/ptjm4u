package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityLoginBinding;
import com.example.ptjm4u.databinding.ActivityRegisterBinding;
import com.example.ptjm4u.service.DB;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;

    DB db = new DB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        onClick();
    }

    private void onClick() {
        activityLoginBinding.btLogin.setOnClickListener(v -> {
            if(checkValidations()){
                String username = activityLoginBinding.etLoginUsername.getText().toString();
                String password = activityLoginBinding.etLoginPassword.getText().toString();


                db.LoginCheckUsernamePassword(username,password, new DB.LoginCallback() {
                    @Override
                    public void onLoginResult(String sentUserId) {
                        if (sentUserId.isEmpty()){
                            Toast.makeText(LoginActivity.this, "Login failed, please try again", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

        });
    }
    private boolean checkValidations() {

        if (TextUtils.isEmpty(activityLoginBinding.etLoginUsername.getText().toString())) {
            activityLoginBinding.tiLoginUsername.setError("Enter Username");
            return false;
        } else if (TextUtils.isEmpty(activityLoginBinding.etLoginPassword.getText().toString())) {
            activityLoginBinding.tiLoginUsername.setError(null);
            activityLoginBinding.tiLoginPassword.setError("Type your password");
            return false;
        } else {
            activityLoginBinding.tiLoginUsername.setError(null);
            activityLoginBinding.etLoginPassword.setError(null);
            return true;
        }
    }

}

