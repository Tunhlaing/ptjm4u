package com.example.ptjm4u.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.JobListModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    String id;
    public MutableLiveData<Boolean> addJobMutableLiveData = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> fetchJobMutableLiveData = new MutableLiveData<>(null);
    List<JobListModel> jobListModelList = new ArrayList<>();



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
    public void fetchJob(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference jobRef = database.getReference("jobs_table");
        jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    JobListModel jobListModel = dataSnapshot.getValue(JobListModel.class);
                    int e = Log.e(TAG, "onDataChange: "+jobListModel );
                    jobListModelList.add(jobListModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}
