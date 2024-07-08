package com.csym030.careerportal;

import static com.csym030.careerportal.AppliedJobsPage.appliedJobFlag;
import static com.csym030.careerportal.LoginController.loginuser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class JobsAdminView extends RecyclerView.Adapter<JobsAdminView.MyViewHolder>{
    //Variable Declaration
    private Context context;
    private List<JobsDao> listItems;
    private List<JobsDao> filterList = new ArrayList<JobsDao>();
    public JobsAdminView(Context context, List<JobsDao> jobList) {

        this.listItems = jobList;
        this.context = context;
        this.filterList = listItems;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //View Declaration
        TextView jobTitle,employer, address,contact;
        Button edit,delete;
        Button apply;

        public MyViewHolder(View view) {
            super(view);

            //View initialisation
            jobTitle = view.findViewById(R.id.title);
            address = view.findViewById(R.id.address);
            employer = view.findViewById(R.id.employer);
            contact = view.findViewById(R.id.contact);
            edit = view.findViewById(R.id.editbtn);
            delete = view.findViewById(R.id.deletebutton);
            apply = view.findViewById(R.id.applybtn);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull JobsAdminView.MyViewHolder holder, int position) {
        final JobsDao item = listItems.get(position);
        //setting value to the views
        holder.jobTitle.setText("Job Name: "+item.getTitle());
        holder.employer.setText("Organisation: "+item.getEmployer());
        holder.address.setText("Address: "+item.getAddress());
        holder.contact.setText("Phone: "+item.getContactNumber());
        if("admin".equalsIgnoreCase(loginuser)){
            holder.edit.setVisibility(View.VISIBLE);
            holder.apply.setVisibility(View.GONE);
            holder.delete.setVisibility(View.VISIBLE);
        }else{
            holder.edit.setVisibility(View.GONE);
            holder.delete.setVisibility(View.GONE);
            if(!appliedJobFlag){
                holder.apply.setVisibility(View.VISIBLE);
            }else{
                holder.apply.setVisibility(View.GONE);
            }

        }

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job=item.getTitle();
                String ph1=item.getContactNumber() ;
                String addr=item.getAddress();
                String org=item.getEmployer();
                DatabaseController db = new DatabaseController(context);
                AppliedJobsDao a2 = new AppliedJobsDao(job,addr, ph1,org,loginuser);
                db.applyJob(a2);
                Snackbar.make(context, v,"Job Applied Successfully ", Snackbar.LENGTH_LONG).show();
                ((Activity) context).finish();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String job=item.getTitle();
                DatabaseController dbController = new DatabaseController(context);
                dbController.deleteJob(job);
                Toast.makeText(context,"Job Deleted Successfully ", Toast.LENGTH_LONG).show();
                ((Activity) context).finish();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
