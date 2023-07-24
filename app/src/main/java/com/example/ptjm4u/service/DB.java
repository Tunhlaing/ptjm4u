package com.example.ptjm4u.service;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.view.activity.RegisterActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.atomic.AtomicBoolean;


public class DB{

    private Context context;
    String id;


    public boolean addUser(String username, int age, String address, String phoneNumber, String password, int userType, String gender, String specializedField, String joinedDateTime){
        Log.e(TAG, "debugCodes: "+ username + " "+ age + address + phoneNumber + password+ userType+gender+specializedField+joinedDateTime);

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user_Table");
            if (id == null) {
                id = myRef.push().getKey();
            }
            RegisterModel registerModel1 = new RegisterModel(id, username, age, address, phoneNumber, password, userType, gender, specializedField, joinedDateTime);
            myRef.child(id).setValue(registerModel1).addOnCompleteListener(task ->{

    });
            return true;
        }

    public interface LoginCallback {
        void onLoginResult(String sentUserId);
    }


    public void LoginCheckUsernamePassword(String username, String password, LoginCallback callback){
            DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference().child("user_Table");
            Query query =loginRef.orderByChild("username").equalTo(username);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                String sentUserId = "";


                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Get the password from the database entry
                        String dbPassword = snapshot.child("password").getValue(String.class);

                        // Compare the database password with the user input password
                        if (dbPassword.equals(password)) {
                            Log.e(TAG, "onDataChange: "+"yes");

                            sentUserId = snapshot.child(("userId")).getValue(String.class);
                            Log.e(TAG, "onDataChange2: "+sentUserId);


                            break;
                        }
                    }
                callback.onLoginResult(sentUserId);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }
    public interface CheckUsernameExistsCallback{
        void onUsernameChecked (boolean usernameExists);

    }
    public void checkUsernameExists(String username, CheckUsernameExistsCallback callback) {
        Log.e(TAG, "username: "+ username);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user_Table");
        Query query = usersRef.orderByChild("username").equalTo(username);
        //Log.e(TAG, "query: "+ query);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isUsernameExists = dataSnapshot.exists();
                callback.onUsernameChecked(isUsernameExists);
                Log.e(TAG, "boolean5: "+ isUsernameExists);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onUsernameChecked(false);
            }
        });
    }



}

