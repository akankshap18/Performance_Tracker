package com.example.performancetracker.Admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.R;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    Context context;
    ArrayList<TaskModel> taskArrayList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskArrayList) {
        this.context = context;
        this.taskArrayList = taskArrayList;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.admin_model_layout1,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {

        TaskModel model = taskArrayList.get(position);

        holder.taskname.setText(model.taskname);
        holder.taskdesc.setText(model.taskdesc);

//        holder.mAssign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent(context, AssignTask.class);
//
//                intent.putExtra("tname", model.taskname);
//                intent.putExtra("tdesc", model.taskdesc);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView taskname, taskdesc;
//        Button mAssign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskname = itemView.findViewById(R.id.tvTask);
            taskdesc = itemView.findViewById(R.id.tvdescription);

//            mAssign = itemView.findViewById(R.id.tvAssign);
        }
    }
}

