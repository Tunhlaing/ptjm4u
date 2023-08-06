package com.example.ptjm4u.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobRepository {

    String id;
    public MutableLiveData<Boolean> addJobMutableLiveData = new MutableLiveData<>(null);


    public void createJob(CreateJobModel createJobModel){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jobs_table");
        if (id == null){
            id = myRef.push().getKey();
        }
        createJobModel.setJobId(id);
        myRef.child(id).setValue(createJobModel).addOnSuccessListener(task ->{
            addJobMutableLiveData.postValue(true);
        }).addOnFailureListener(task->{
            addJobMutableLiveData.postValue(false);
        });
    }

}
