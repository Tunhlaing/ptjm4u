package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityMainBinding;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        onClick();

    }

    private void onClick() {
        activityMainBinding.btLogin.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));

        });
        activityMainBinding.btRegisterPoster.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class).putExtra("registerType",1));


        });
        activityMainBinding.btRegisterSeeker.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class).putExtra("registerType",0));


        });
    }
}
