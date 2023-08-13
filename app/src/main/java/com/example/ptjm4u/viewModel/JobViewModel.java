package com.example.ptjm4u.viewModel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.JobListModel;
import com.example.ptjm4u.repository.JobRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class JobViewModel extends ViewModel {
    JobRepository jobRepository = new JobRepository();
    public LiveData<Boolean> addJobMutableLiveData = jobRepository.addJobMutableLiveData;
    public MutableLiveData<List<JobListModel>> jobMutableLiveData = jobRepository.getJobListModelMutableLiveData();

    public void createJob(CreateJobModel createJobModel){
        jobRepository.createJob(createJobModel);

    }
    public MutableLiveData<List<JobListModel>> getJobMutableLiveData() {
        return jobMutableLiveData;
    }


}
