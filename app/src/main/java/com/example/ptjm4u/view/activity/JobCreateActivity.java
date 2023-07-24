package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityJobCreateBinding;
import com.example.ptjm4u.databinding.ActivityLoginBinding;

public class JobCreateActivity extends AppCompatActivity {

    private ActivityJobCreateBinding activityJobCreateBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJobCreateBinding = ActivityJobCreateBinding.inflate(getLayoutInflater());
        setContentView(activityJobCreateBinding.getRoot());
        onClick();
    }

    private void onClick() {
        activityJobCreateBinding.btPost.setOnClickListener(v -> {

        });
    }
    private boolean checkJoblist() {

        if (TextUtils.isEmpty(activityJobCreateBinding.etJobCategory.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError("Enter job category");
            return false;
        } else if (TextUtils.isEmpty(activityJobCreateBinding.etJobDescription.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError("enter job description");
            return false;
        }else if (TextUtils.isEmpty(activityJobCreateBinding.etJobLocation.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError("enter job location");
            return false;
        }else if (TextUtils.isEmpty(activityJobCreateBinding.etJobDuration.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError(null);
            activityJobCreateBinding.tiJobDuration.setError("enter job durations(hour");
            return false;
        }else {
            activityJobCreateBinding.tiJobCategory.setError(null);
            return true;
        }
    }


}