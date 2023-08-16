package com.example.ptjm4u.view.activity.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ptjm4u.R;

public class JobListDetailFragment extends Fragment {



    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_job_list_detail, container, false);

       ((TextView)v.findViewById(R.id.tvJobCategorys)).setText(this.getArguments().getString("jobCategory"));


        return v;
    }
}