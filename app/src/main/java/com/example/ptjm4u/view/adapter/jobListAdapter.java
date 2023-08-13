package com.example.ptjm4u.view.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptjm4u.R;
import com.example.ptjm4u.databinding.ListItemJobsBinding;
import com.example.ptjm4u.model.datamodel.CreateJobModel;
import com.example.ptjm4u.model.datamodel.JobListModel;

import org.w3c.dom.Text;

import java.util.List;

public class jobListAdapter extends RecyclerView.Adapter<jobListAdapter.JobListViewHolder> {

    List<JobListModel> jobListModelList;

    public jobListAdapter(List<JobListModel> jobListModelList) {
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
    public void onBindViewHolder(@NonNull JobListViewHolder holder, int position) {
        holder.tvDescription.setText(jobListModelList.get(position).getJobDescription());
        holder.tvKyat.setText(String.valueOf(jobListModelList.get(position).getOfferPrice())+"Kyats");
        holder.tvDate.setText(jobListModelList.get(position).getJobCreatedDateTime());
        holder.tvDuration.setText(jobListModelList.get(position).getJobDuration());
        holder.tv_Status.setText(String.valueOf(jobListModelList.get(position).getJobStatus()));
        holder.tv_JobCategory.setText(jobListModelList.get(position).getJobCategory());

        if(jobListModelList.get(position).getJobStatus()==0){
            Log.e(TAG, "getJobStatus: "+ jobListModelList.get(position).getJobStatus());
            holder.itemView.setBackgroundColor(R.color.newColor);
        } else if (jobListModelList.get(position).getJobStatus()==1) {
            holder.itemView.setBackgroundColor(R.color.pendingColor);
        } else if (jobListModelList.get(position).getJobStatus()==3) {
            holder.itemView.setBackgroundColor(R.color.resolveColor);
        }

    }

    @Override
    public int getItemCount() {
        return jobListModelList != null ? jobListModelList.size() : 0;
    }


    class JobListViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription,tvDate,tvDuration,tvKyat,tv_Status,tvJobDescription,tv_JobCategory;

    public JobListViewHolder(@NonNull View v) {
        super(v);
        tvDescription =v.findViewById(R.id.TvDescription);
        tvJobDescription =v.findViewById(R.id.tvJobDescription);
        tvDate =v.findViewById(R.id.tvDate);
        tvDuration = v.findViewById(R.id.tvDuration);
        tvKyat = v.findViewById(R.id.tvKyats);
        tv_Status = v.findViewById(R.id.tv_Status);
        tv_JobCategory = v.findViewById(R.id.tv_JobCategory);


    }
}
}
