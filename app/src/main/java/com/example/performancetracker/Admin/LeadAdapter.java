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

public class LeadAdapter extends RecyclerView.Adapter<LeadAdapter.ViewHolder> {

    Context context;
    ArrayList<LeadModel> leadArrayList;

    public LeadAdapter(Context context, ArrayList<LeadModel> leadArrayList) {
        this.context = context;
        this.leadArrayList = leadArrayList;
    }

    @NonNull
    @Override
    public LeadAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.admin_lead_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadAdapter.ViewHolder holder, int position) {

        LeadModel model = leadArrayList.get(position);

        holder.leadname.setText(model.leadname);
        holder.leademail.setText(model.leademail);
        holder.leadphone.setText(model.leadphone);
    }

    @Override
    public int getItemCount() {
        return leadArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView leadname, leademail, leadphone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            leadname = itemView.findViewById(R.id.tvName);
            leademail = itemView.findViewById(R.id.tvEmail);
            leadphone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
