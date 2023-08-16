package com.example.ptjm4u.view.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptjm4u.R;
import com.example.ptjm4u.model.datamodel.JobListModel;
import com.example.ptjm4u.view.activity.fragment.JobListDetailFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class jobListAdapter extends RecyclerView.Adapter<jobListAdapter.JobListViewHolder> {
    Context context;

    List<JobListModel> jobListModelList;

    public jobListAdapter(Context context, List<JobListModel> jobListModelList) {
        this.context = context;
        this.jobListModelList = jobListModelList;
    }

    @NonNull
    @Override
    public JobListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_jobs,parent,false);
        return new JobListViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvDescription.setText(jobListModelList.get(position).getJobDescription());
        holder.tvKyat.setText(String.valueOf(jobListModelList.get(position).getOfferPrice())+"Kyats");
        holder.tvDate.setText(jobListModelList.get(position).getJobCreatedDateTime());
        holder.tvDuration.setText(jobListModelList.get(position).getJobDuration());
        int status = jobListModelList.get(position).getJobStatus();
        holder.tv_JobCategory.setText(jobListModelList.get(position).getJobCategory());
        Log.e(TAG, "getJobStatus: "+ status);

        if (status==1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.pendingColor));
            holder.tv_Status.setText("pending");
        } else if (status==3) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.resolveColor));
            holder.tv_Status.setText("resolved");

        }else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.newColor));
            holder.tv_Status.setText("new");

        }
//        holder.btDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                JobListModel clickedItem = jobListModelList.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putString("jobCategory" , clickedItem.getJobCategory());
//                bundle.putString("jobDescription" , clickedItem.getJobDescription());
//                bundle.putString("jobDuration", clickedItem.getJobDuration());
//                bundle.putString("jobDuration", clickedItem.getJobCreatedDateTime());
//                bundle.putString("jobDuration", clickedItem.getContactNumber());
//                bundle.putString("jobDuration", clickedItem.getDifficultyLevel());
//                bundle.putString("jobDuration", clickedItem.getRequireLevel());
//                bundle.putInt("jobDuration", clickedItem.getOfferPrice());
//                bundle.putString("jobDuration", clickedItem.getJobLocation());
//                bundle.putString("jobDuration", clickedItem.getJobLocation());
//                JobListDetailFragment jobListDetailFragment = new JobListDetailFragment();
//                jobListDetailFragment.setArguments(bundle);
//
//
//            }
//        });
        holder.btAccept.setOnClickListener(v -> {
            jobListModelList.get(position).setJobStatus(3);
            Log.e(TAG, "onBindViewHolder: "+position );
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        return jobListModelList != null ? jobListModelList.size() : 0;
    }


    class JobListViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription,tvDate,tvDuration,tvKyat,tv_Status,tvJobDescription,tv_JobCategory,btDetail;
        ImageView btAccept,btRemove;


    public JobListViewHolder(@NonNull View v) {
        super(v);
        tvDescription =v.findViewById(R.id.TvDescription);
        tvJobDescription =v.findViewById(R.id.tvJobDescription);
        tvDate =v.findViewById(R.id.tvDate);
        tvDuration = v.findViewById(R.id.tvDuration);
        tvKyat = v.findViewById(R.id.tvKyats);
        tv_Status = v.findViewById(R.id.tv_Status);
        tv_JobCategory = v.findViewById(R.id.tv_JobCategory);
        btDetail = v.findViewById(R.id.btDetail);
        btAccept = v.findViewById(R.id.btAccept);
        btRemove = v.findViewById(R.id.btRemove);


    }
}
}
