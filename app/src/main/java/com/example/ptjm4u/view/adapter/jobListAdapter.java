package com.example.ptjm4u.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class jobListAdapter extends RecyclerView.Adapter<jobListAdapter.jobListViewHolder> {

    @NonNull
    @Override
    public jobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull jobListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class jobListViewHolder extends RecyclerView.ViewHolder{

        public jobListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
