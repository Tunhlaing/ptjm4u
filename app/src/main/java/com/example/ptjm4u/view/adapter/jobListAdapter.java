package com.example.ptjm4u.view.adapter;

import static android.content.ContentValues.TAG;

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

    @Override
    public void onBindViewHolder(@NonNull JobListViewHolder holder, int position) {
        int e = Log.e(TAG, "onBindViewHolder: "+position);
        holder.tvDescription.setText(jobListModelList.get(position).getJobDescription());
            holder.tvKyat.setText(jobListModelList.get(position).getOfferPrice());
    }

    @Override
    public int getItemCount() {
        return jobListModelList.size();
    }

    class JobListViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription,tvData,tvDuration,tvKyat,tv_Status,tvJobDescription;

    public JobListViewHolder(@NonNull View v) {
        super(v);
        tvDescription =v.findViewById(R.id.TvDescription);
        tvJobDescription =v.findViewById(R.id.tvJobDescription);
        tvData =v.findViewById(R.id.tvDate);
        tvDuration = v.findViewById(R.id.tvDuration);
        tvKyat = v.findViewById(R.id.tvKyats);
        tv_Status = v.findViewById(R.id.tv_Status);


    }
}
}
