package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.ptjm4u.databinding.ActivityLoginBinding;
import com.example.ptjm4u.service.DB;
import com.example.ptjm4u.util.Utils;


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
                            Utils.showToast(LoginActivity.this,"login failed, please check your username and password");
                        }else {
                            Utils.showToast(LoginActivity.this,"login success");
                            startActivity(new Intent(LoginActivity.this, JobListActivity.class).putExtra("sentUserId",sentUserId));

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

