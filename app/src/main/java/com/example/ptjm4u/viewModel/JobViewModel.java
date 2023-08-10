package com.example.ptjm4u.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.JobListModel;
import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.repository.JobRepository;

import java.util.List;

public class JobViewModel extends ViewModel {
    JobRepository jobRepository = new JobRepository();


    public LiveData<Boolean> addJobMutableLiveData = jobRepository.addJobMutableLiveData;
    public LiveData<Boolean> fetchJobMutableLiveData = jobRepository.fetchJobMutableLiveData;
    public void createJob(CreateJobModel createJobModel){
        Log.e("testAsdf","3");
        jobRepository.createJob(createJobModel);

    }
    public void fetchJob(){
        if(fetchJobMutableLiveData == null){
            jobRepository.fetchJob();
        }
    }

}
