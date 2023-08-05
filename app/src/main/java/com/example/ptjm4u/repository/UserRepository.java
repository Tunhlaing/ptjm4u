package com.example.ptjm4u.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//this class is to save data
public class UserRepository {
    String id;
    public MutableLiveData<Boolean> addUserMutableLiveData = new MutableLiveData<>(null);
    public void addUser(RegisterModel registerModel){
        Log.e("testAsdf","4");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_Table");
        if (id == null){
            id = myRef.push().getKey();
                    }
        registerModel.setUserId(id);
        myRef.child(id).setValue(registerModel).addOnSuccessListener(task ->{
            Log.e("testAsdf","5");

            addUserMutableLiveData.postValue(true);
        }).addOnFailureListener(task->{
           addUserMutableLiveData.postValue(false);
        });
    }
    public void createJob(CreateJobModel createJobModel){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jobs_table");
        if (id == null){
            id = myRef.push().getKey();
        }
        createJobModel.setJobId(id);
        myRef.child(id).setValue(createJobModel).addOnSuccessListener(task ->{
            addUserMutableLiveData.postValue(true);
        }).addOnFailureListener(task->{
            addUserMutableLiveData.postValue(false);
        });
    }


}
