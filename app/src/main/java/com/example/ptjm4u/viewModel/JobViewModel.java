package com.example.ptjm4u.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.repository.JobRepository;

public class JobViewModel extends ViewModel {
    JobRepository jobRepository = new JobRepository();
    public LiveData<Boolean> addJobMutableLiveData = jobRepository.addJobMutableLiveData;
    public void createJob(CreateJobModel createJobModel){
        Log.e("testAsdf","3");
        jobRepository.createJob(createJobModel);

    }

}
