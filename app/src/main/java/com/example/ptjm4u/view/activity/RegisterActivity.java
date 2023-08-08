package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityRegisterBinding;
import com.example.ptjm4u.model.datamodel.RegisterModel;
import com.example.ptjm4u.util.Utils;
import com.example.ptjm4u.viewModel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding activityRegisterBinding;
    int genderId = 0;
    String gender= "";
    String specializedField = null;
    UserViewModel userViewModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        setToolbar();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        observeViewModel();

        onclick();



    }
    protected void onResume() {
        super.onResume();
        setSpinner();
    }

    private void observeViewModel() {
        userViewModel.addUserLiveData.observe(this, isSuccess ->{
            Log.e("testAsdf","6 + RegisterActivity");
            if(isSuccess!=null) {
                if (isSuccess) {
                    Utils.showToast(RegisterActivity.this, "Register Successful");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Utils.showToast(RegisterActivity.this, "Register failed");
                }
            }

        });
        userViewModel.addUserCheckLiveData.observe(this,isSuccess ->{
            if(isSuccess!=null){
                if(isSuccess){
                    activityRegisterBinding.tiUsername.setError("username already existing");
                    Log.e("testAsdf"," 2+ username is no ok");

                }else {
                    Log.e("testAsdf"," 2+ username is ok");
                    String username = activityRegisterBinding.etUsername.getText().toString().toLowerCase();
                    int age = Integer.parseInt(activityRegisterBinding.etAge.getText().toString());
                    String address = activityRegisterBinding.etAddress.getText().toString();
                    String phoneNumber = activityRegisterBinding.etPhoneNumber.getText().toString();
                    String passWord = activityRegisterBinding.etPassword.getText().toString();
                    Log.e(TAG, "checkValidations: " + specializedField);
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String joinedDateTime = dateFormat.format(currentDate);
                    int userType = getIntent().getIntExtra("registerType", 1);
                    if (genderId == 0){
                        gender = "male";
                    }else {
                        gender = "female";
                    }
                    RegisterModel registerModel = new RegisterModel(null, username, age, address, phoneNumber, passWord, userType, gender, specializedField, joinedDateTime);
                    userViewModel.addUser(registerModel);

                }
            }
        });

    }

    public void setSpinner(){
        String [] specializedField = getResources().getStringArray(R.array.specialized_fields_drop_drown);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,specializedField);
        activityRegisterBinding.spinner.setAdapter(adapter);

    }
    private void setToolbar() {
        ActionBar toolbar = getSupportActionBar();
        if(getIntent().getIntExtra("registerType", 2) == 1){
            toolbar.setTitle("Register(poster)");
        }else {
            toolbar.setTitle("Register(seeker)");

        }


    }

    private void onclick() {
        activityRegisterBinding.rgGender.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == activityRegisterBinding.rbMale.getId()) {
                genderId = 0;
            } else {
                genderId = 1;
            }

        }));
        activityRegisterBinding.spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                specializedField = selectedItem;
                Log.e(TAG, "checkValidations1: " + specializedField);

            }
        });
        activityRegisterBinding.btRegister.setOnClickListener(v->{
            String username = activityRegisterBinding.etUsername.getText().toString().toLowerCase();
            int userType = getIntent().getIntExtra("registerType", 1);


            Log.e("testAsdf","1 + click Register button");

            if(checkValidations()){
                Log.e("usertype", String.valueOf(userType));

                userViewModel.checkUserNameExist(username , userType);
            }


        });
    }
    private boolean checkValidations() {

        if (TextUtils.isEmpty(activityRegisterBinding.etUsername.getText().toString())) {
            activityRegisterBinding.tiUsername.setError("Enter Username");
            return false;
        } else if (TextUtils.isEmpty(activityRegisterBinding.etAge.getText().toString())) {
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError("Enter Age");
            return false;
        } else if (!(Integer.parseInt(activityRegisterBinding.etAge.getText().toString()) >= 18 && Integer.parseInt(activityRegisterBinding.etAge.getText().toString()) <=55 )){
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError("only can use between 18 yeasr old and 55 years old");
            return false;
        }else if (TextUtils.isEmpty(activityRegisterBinding.etPhoneNumber.getText().toString())) {
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiPhoneNumber.setError("Enter Phone Number");
            return false;
        } else if (TextUtils.isEmpty(activityRegisterBinding.etAddress.getText().toString())) {
            activityRegisterBinding.tiPhoneNumber.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiAddress.setError("Enter address");
            return false;
        } else if (TextUtils.isEmpty(activityRegisterBinding.etPassword.getText().toString())) {
            activityRegisterBinding.tiAddress.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiPhoneNumber.setError(null);

            activityRegisterBinding.tiPassword.setError("please fill password");
            return false;
        } else if (!(activityRegisterBinding.etPassword.length() >= 6 && activityRegisterBinding.etPassword.length() <= 15)) {
            activityRegisterBinding.tiAddress.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiPhoneNumber.setError(null);
            activityRegisterBinding.tiAddress.setError(null);
            activityRegisterBinding.tiPassword.setError(null);
            activityRegisterBinding.tiPassword.setError("Password must be between 6 and 15 characters");
            return false;
        } else if (TextUtils.isEmpty(activityRegisterBinding.etConfirmPassword.getText().toString())) {
            activityRegisterBinding.tiPassword.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiPhoneNumber.setError(null);
            activityRegisterBinding.tiAddress.setError(null);
            activityRegisterBinding.tiPassword.setError(null);
            activityRegisterBinding.tiConfirmPassword.setError("need confirm password");
            return false;
        } else if (!activityRegisterBinding.etConfirmPassword.getText().toString().equals(activityRegisterBinding.etPassword.getText().toString())) {
            activityRegisterBinding.tiPassword.setError(null);
            activityRegisterBinding.tiUsername.setError(null);
            activityRegisterBinding.tiAge.setError(null);
            activityRegisterBinding.tiPhoneNumber.setError(null);
            activityRegisterBinding.tiAddress.setError(null);
            activityRegisterBinding.tiConfirmPassword.setError(null);
            activityRegisterBinding.tiConfirmPassword.setError("password don't match");
            return false;
        } else if (specializedField == null) {
            activityRegisterBinding.spinner.setError("choose your specialized");
            return false;
        } else {
            return true;
        }
    }

}