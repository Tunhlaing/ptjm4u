package com.example.ptjm4u.view.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ActivityMainBinding;
import com.example.ptjm4u.databinding.ActivityRegisterBinding;
import com.example.ptjm4u.service.DB;
import com.example.ptjm4u.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding activityRegisterBinding;
    int genderId = 0;
    String gender= "";

    DB db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterBinding.getRoot());
        onclick();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.specialized_fields_drop_drown, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        activityRegisterBinding.spinner.setAdapter(adapter);

    }

    private void onclick() {
        activityRegisterBinding.rgGender.setOnCheckedChangeListener(((group, checkedId) -> {
            if (checkedId == activityRegisterBinding.rbMale.getId()) {
                genderId = 0;
            } else {
                genderId = 1;
            }

        }));
        activityRegisterBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                String specializedField = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        activityRegisterBinding.btRegister.setOnClickListener(v->{

            if(checkValidations()){

                String username = activityRegisterBinding.etUsername.getText().toString();
                int age = Integer.parseInt(activityRegisterBinding.etAge.getText().toString());
                String address = activityRegisterBinding.etAddress.getText().toString();
                String phoneNumber = activityRegisterBinding.etPhoneNumber.getText().toString();
                String passWord = activityRegisterBinding.etPassword.getText().toString();
                String specializedField = activityRegisterBinding.spinner.getSelectedItem().toString();


                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String joinedDateTime = dateFormat.format(currentDate);
                int userType = getIntent().getIntExtra("registerType", 1);
                if (genderId == 0){
                    gender = "male";
                }else {
                    gender = "female";
                }
                db = new DB();

                db.checkUsernameExists(username, new DB.CheckUsernameExistsCallback() {
                    @Override
                    public void onUsernameChecked(boolean usernameExists) {
                        if (usernameExists){
                            Log.e(TAG, "onUsernameChecked: "+usernameExists);
                            activityRegisterBinding.tiUsername.setError("username already existing, please choose other");
                        }
                        else {
                            if(db.addUser(username,age,address,phoneNumber,passWord,
                                    userType,gender,specializedField,joinedDateTime)){
                                Utils.showToast(RegisterActivity.this,"Register Successful");
                                //db.isUsernameExists = null;
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();

                            }else {
                                Utils.showToast(RegisterActivity.this,"Register failed");

                            }

                        }
                    }
                });


            }
            else {
                Utils.showToast(RegisterActivity.this,"please check your information");

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
        } else {
            return true;
        }
    }

}