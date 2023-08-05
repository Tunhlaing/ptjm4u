package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RadioButton;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityJobCreateBinding;
import com.example.ptjm4u.databinding.ActivityLoginBinding;
import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.service.DB;
import com.example.ptjm4u.viewModel.UserViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobCreateActivity extends AppCompatActivity {

    private ActivityJobCreateBinding activityJobCreateBinding;
    String difficultyLevel = "";
    String requireLevel = "";
    UserViewModel userViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJobCreateBinding = ActivityJobCreateBinding.inflate(getLayoutInflater());
        setContentView(activityJobCreateBinding.getRoot());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        onClick();

    }

    private void onClick() {
        activityJobCreateBinding.rgDifficulty.setOnCheckedChangeListener((group, checkedId) -> {

            RadioButton radioButton = findViewById(checkedId);
            difficultyLevel = radioButton.getText().toString();

        });
        activityJobCreateBinding.rgRequire.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radioButton = findViewById(checkedId);
            requireLevel = radioButton.getText().toString();
        });
        activityJobCreateBinding.btPost.setOnClickListener(v -> {
            if(checkJobList()){
                String userId = getIntent().getStringExtra("sentUserIDFromJobList");
                String jobCategory = activityJobCreateBinding.etJobCategory.getText().toString();
                String jobDescription = activityJobCreateBinding.etJobDescription.getText().toString();
                String jobLocation = activityJobCreateBinding.etJobLocation.getText().toString();
                String jobDuration = activityJobCreateBinding.etJobDuration.getText().toString();
                String contactNumber = activityJobCreateBinding.etContactNumber.getText().toString();
                int requireWorker = Integer.parseInt(activityJobCreateBinding.etRequireWorker.getText().toString());
                int offerPrice = Integer.parseInt(activityJobCreateBinding.etOfferPrice.getText().toString());
                int jobStatus = 0;
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String jobCreatedDateTime = dateFormat.format(currentDate);
                CreateJobModel createJobModel = new CreateJobModel(userId,null, jobCategory,jobDescription,jobLocation,jobCreatedDateTime,contactNumber,requireWorker,offerPrice,jobStatus,difficultyLevel,requireLevel,jobCreatedDateTime);
                userViewModel.createJob(createJobModel);
                finish();
            }

        });
    }
    private boolean checkJobList() {

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
            activityJobCreateBinding.tiJobDuration.setError("enter job durations(hour)");
            return false;
        }else if (TextUtils.isEmpty(activityJobCreateBinding.etContactNumber.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError(null);
            activityJobCreateBinding.tiJobDuration.setError(null);
            activityJobCreateBinding.tiContactNumber.setError("enter contact number");
            return false;
        }else if (TextUtils.isEmpty(activityJobCreateBinding.etRequireWorker.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError(null);
            activityJobCreateBinding.tiJobDuration.setError(null);
            activityJobCreateBinding.tiContactNumber.setError(null);
            activityJobCreateBinding.tiRequireWorker.setError("enter require worker");
            return false;
        }else if (TextUtils.isEmpty(activityJobCreateBinding.etRequireWorker.getText().toString())) {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError(null);
            activityJobCreateBinding.tiJobDuration.setError(null);
            activityJobCreateBinding.tiContactNumber.setError(null);
            activityJobCreateBinding.tiRequireWorker.setError(null);
            activityJobCreateBinding.tiOfferPrice.setError("enter offer prices (kyats)");
            return false;
        }else {
            activityJobCreateBinding.tiJobCategory.setError(null);
            activityJobCreateBinding.tiJobDescription.setError(null);
            activityJobCreateBinding.tiJobLocation.setError(null);
            activityJobCreateBinding.tiJobDuration.setError(null);
            activityJobCreateBinding.tiContactNumber.setError(null);
            activityJobCreateBinding.tiRequireWorker.setError(null);
            activityJobCreateBinding.tiOfferPrice.setError(null);

            return true;
        }
    }
    //(String jobId, String jobCategory, String jobDescription, String jobLocation, String jobDuration, String contactNumber,
    // int requireWorker, int offerPrice, int jobStatus, String difficultyLevel, String requireLevel, String jobCreatedDateTime



}