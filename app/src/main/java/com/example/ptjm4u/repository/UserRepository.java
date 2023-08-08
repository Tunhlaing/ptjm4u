package com.example.ptjm4u.repository;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.util.SharePrefs;
import com.example.ptjm4u.view.activity.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//this class is to save data
public class UserRepository {
    String id;
    public MutableLiveData<Boolean> addUserMutableLiveData = new MutableLiveData<>(null);
    public MutableLiveData<Boolean> addUserCheckMutableLiveData = new MutableLiveData<>(null);

    public MutableLiveData<Boolean> checkLoginMutableLiveData = new MutableLiveData<>(null);

    public void addUser(RegisterModel registerModel){
        Log.e("testAsdf","4 + Call addUser for UserRespitory");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user_Table");
        if (id == null){
            id = myRef.push().getKey();
                    }
        registerModel.setUserId(id);
        myRef.child(id).setValue(registerModel).addOnSuccessListener(task ->{
            Log.e("testAsdf","5 + add Value to MutableLiveData");

            addUserMutableLiveData.postValue(true);
        }).addOnFailureListener(task->{
           addUserMutableLiveData.postValue(false);
        });
    }
//    public void checkUserNameExist(String username, int usertype){
//
//        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user_Table");
//        Query query = usersRef.orderByChild("username").equalTo(username);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                addUserCheckMutableLiveData.postValue(snapshot.exists());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                addUserCheckMutableLiveData.postValue(false);
//            }
//        });
//
//    }
    public LiveData<Boolean> checkUserNameExist(String username , int usertype){
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference().child("user_Table");
        Query query =loginRef.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isUsernameExits = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int dbUserId = snapshot.child("userType").getValue(int.class);
                    if (dbUserId == usertype) {
                        isUsernameExits = true;
                        break;
                    }
                }
                addUserCheckMutableLiveData.postValue(isUsernameExits);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                addUserCheckMutableLiveData.postValue(false);

            }
        });
        return addUserCheckMutableLiveData;
    }
    public LiveData<Boolean> checkLogin(Context context, String username , String password,int userType){
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference().child("user_Table");
        Query query =loginRef.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isAuthenticated = false;
                String userId = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String dbPassword = snapshot.child("password").getValue(String.class);
                    int dbUserType = snapshot.child("userType").getValue(int.class);
                    userId = snapshot.child(("userId")).getValue(String.class);
                    if (dbPassword.equals(password) && userType ==dbUserType) {
                        isAuthenticated = true;
                        int e = Log.e(TAG, "onDataChange: "+ context);
                        int i = Log.e(TAG, "onDataChange: "+ userId);
                        SharePrefs.setStringPref(context, "userId",userId);
                        break;
                    }
                }
                checkLoginMutableLiveData.postValue(isAuthenticated);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                checkLoginMutableLiveData.postValue(false);

            }
        });
        return checkLoginMutableLiveData;
    }




}
