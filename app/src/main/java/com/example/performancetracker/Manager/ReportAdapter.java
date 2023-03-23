package com.example.performancetracker.Manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.R;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    Context context;
    ArrayList<ReportModel> reportArrayList;

    public ReportAdapter(Context context, ArrayList<ReportModel> reportArrayList) {
        this.context = context;
        this.reportArrayList = reportArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.report_layout,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ReportModel model = reportArrayList.get(position);

        holder.report.setText(model.report);
        holder.doneby.setText(model.doneby);
    }

    @Override
    public int getItemCount() {
        return reportArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView report, doneby;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            report = itemView.findViewById(R.id.tvReport);
            doneby = itemView.findViewById(R.id.tvDone);
        }
    }
}

