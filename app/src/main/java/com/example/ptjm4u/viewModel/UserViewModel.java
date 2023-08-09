package com.example.ptjm4u.viewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.repository.UserRepository;

//to class to observe data changes
public class UserViewModel extends ViewModel {
    UserRepository userRepository = new UserRepository();
    public LiveData<Boolean> addUserLiveData = userRepository.addUserMutableLiveData;
    public LiveData<Boolean> addUserCheckLiveData = userRepository.addUserCheckMutableLiveData;

    public LiveData<Boolean> loginCheckLiveData = userRepository.checkLoginMutableLiveData;



    public void addUser(RegisterModel registerModel){
        Log.e("testAsdf","3 + userViewModel");
        userRepository.addUser(registerModel);

    }
    public void checkUserNameExist(String username, int userType){
       // Log.e("usertype" , usertype + "userViewModel");

        userRepository.checkUserNameExist(username, userType);
    }
    public void checkLogin(Context context, String username, String password, int userType){
        userRepository.checkLogin(context, username, password,userType);
    }
}
