package com.example.ptjm4u.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        if(isInternetAvailable()){
            onClick();
        }else {
            showInternetNotAvailableNotice();

        }

    }
   private void showInternetNotAvailableNotice() {
        Snackbar.make(findViewById(android.R.id.content),
                      "Internet is not available. Please check your connection.",
                        Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", view -> finish()) // Optional: Finish the activity when user taps "OK"
                .show();

    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void onClick() {
        activityMainBinding.btLogin.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));

        });
        activityMainBinding.btRegisterPoster.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class).putExtra("registerType",1));


        });
        activityMainBinding.btRegisterSeeker.setOnClickListener(v->{
            startActivity(new Intent(this,RegisterActivity.class).putExtra("registerType",0));


        });
    }
}
