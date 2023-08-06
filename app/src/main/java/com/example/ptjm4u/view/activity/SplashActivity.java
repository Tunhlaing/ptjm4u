package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ptjm4u.R;
import com.example.ptjm4u.util.SharePrefs;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (SharePrefs.getBooleanPref(this, "isLoggedIn")) {
            startActivity(new Intent(this, JobListActivity.class));
            finish();
        } else {
            startActivity((new Intent(this, MainActivity.class)));
            finish();
        }
    }
}