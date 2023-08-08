package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityLoginBinding;
import com.example.ptjm4u.util.SharePrefs;
import com.example.ptjm4u.util.Utils;
import com.example.ptjm4u.viewModel.UserViewModel;


public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding activityLoginBinding;
    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        observeViewModel();
        onClick();
        setToolbar();
    }
    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("Login");
               }


    private void onClick() {
        activityLoginBinding.btLogin.setOnClickListener(v -> {
            ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
            loadingProgressBar.setVisibility(View.VISIBLE);

            if(checkValidations()){
                String username = activityLoginBinding.etLoginUsername.getText().toString();
                String password = activityLoginBinding.etLoginPassword.getText().toString();
                int userType = 1;
                userViewModel.checkLogin(LoginActivity.this,username,password,userType);
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
    private void observeViewModel() {
        userViewModel.loginCheckLiveData.observe(this, isAuthenticated ->{
            ProgressBar loadingProgressBar = findViewById(R.id.loadingProgressBar);
            loadingProgressBar.setVisibility(View.INVISIBLE);

            int e = Log.e(TAG, "isSuccess1: "+ userViewModel.loginCheckLiveData.getValue() );
            if(isAuthenticated!=null) {
                if (isAuthenticated) {
                    Utils.showToast(LoginActivity.this, "Login Successful");
                    startActivity(new Intent(LoginActivity.this, JobListActivity.class));
                    SharePrefs.setBooleanPref(this, "isLoggedIn", true);

                    finish();
                } else {
                    Utils.showToast(LoginActivity.this, "Login failed");
                }
            }


        });



    }


}

