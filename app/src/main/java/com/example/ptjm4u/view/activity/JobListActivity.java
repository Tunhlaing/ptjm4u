package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityJobListBinding;
import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.JobListModel;
import com.example.ptjm4u.util.SharePrefs;
import com.example.ptjm4u.util.Utils;
import com.example.ptjm4u.view.adapter.jobListAdapter;
import com.example.ptjm4u.viewModel.JobViewModel;

import java.util.ArrayList;
import java.util.List;

public class JobListActivity extends AppCompatActivity {

    private ActivityJobListBinding activityJobListBinding;
    JobViewModel jobViewModel;
    jobListAdapter jobListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityJobListBinding = ActivityJobListBinding.inflate(getLayoutInflater());
        setContentView(activityJobListBinding.getRoot());
        jobViewModel = new ViewModelProvider(this).get(JobViewModel.class);
        setToolbar();
        observeViewModel();
        onClick();

    }

    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        toolbar.setTitle("Job list");
    }


    private void onClick() {
        activityJobListBinding.ivJobList.setOnClickListener(v -> {
            String sentUserId = getIntent().getStringExtra("sentUserId");
            int e = Log.e(TAG, "onClick: " + sentUserId);
            startActivity(new Intent(JobListActivity.this, JobCreateActivity.class).putExtra("sentUserIDFromJobList", sentUserId));

        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onBackPressed() {
        //super.onBackPressed();
        showDialog("Are You Sure, You Want To Exit?", false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.navFilter: {
//                if (!isFilter) {
//                    isFilter = true;
//                    item.setIcon(R.drawable.baseline_filter_list_off_24);
////                    setAdapter(myDatabase.getAllStatus(1, SharePrefs.getIntPref(this, "userId")));
//
//                } else {
//                    isFilter = false;
//                    item.setIcon(R.drawable.baseline_filter_alt_24);
////                    setAdapter(myDatabase.getAllStatus(0, SharePrefs.getIntPref(this, "userId")));
//                }
//
//                break;
//            }
            case R.id.navLogout: {
                showDialog("Are You Sure To Logout", true);
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDialog(String message, boolean isLogout) {
        new AlertDialog.Builder(this).setMessage(message).setCancelable(false).setPositiveButton("yes", (dialog, which) -> {
            if (isLogout) {
                SharePrefs.clearPref(JobListActivity.this);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.clear();
//                    editor.apply();

                startActivity(new Intent(JobListActivity.this, MainActivity.class));
                finish();
            } else {
                JobListActivity.super.onBackPressed();
            }
        }).setNegativeButton("No", null).show();
    }

    private void observeViewModel() {
            jobViewModel.getJobMutableLiveData().observe(this, jobListModelList ->{
                jobListAdapter= new jobListAdapter(this,jobListModelList);
                activityJobListBinding.rvJoblist.setLayoutManager(new LinearLayoutManager(this));
                activityJobListBinding.rvJoblist.setAdapter(jobListAdapter);
                jobListAdapter.notifyDataSetChanged();


            });



    }

}