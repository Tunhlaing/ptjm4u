package com.example.ptjm4u.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.repository.UserRepository;

//to class to observe data changes
public class UserViewModel extends ViewModel {
    UserRepository userRepository = new UserRepository();
    public LiveData<Boolean> addUserLiveData = userRepository.addUserMutableLiveData;

    public void addUser(RegisterModel registerModel){
        Log.e("testAsdf","3");
        userRepository.addUser(registerModel);

    }
    public void createJob(CreateJobModel createJobModel){
        userRepository.createJob(createJobModel);
    }
}
