package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ptjm4u.databinding.ActivityJobListBinding;

public class JobListActivity extends AppCompatActivity {

    private ActivityJobListBinding activityJobListBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJobListBinding = ActivityJobListBinding.inflate(getLayoutInflater());
        setContentView(activityJobListBinding.getRoot());
        onClick();

    }

    private void onClick() {
        activityJobListBinding.ivJobList.setOnClickListener(v -> {
            String sentUserId =getIntent().getStringExtra("sentUserId");
            int e = Log.e(TAG, "onClick: "+sentUserId );
            startActivity(new Intent(JobListActivity.this, JobCreateActivity.class).putExtra("sentUserIDFromJobList",sentUserId));

        });
    }


}