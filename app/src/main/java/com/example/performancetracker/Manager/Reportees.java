package com.example.performancetracker.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.performancetracker.Admin.DeleteReport;
import com.example.performancetracker.Admin.ViewReport;
import com.example.performancetracker.R;

public class Reportees extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_fragment_reportees,container,false);
        Button viewTask = (Button) view.findViewById(R.id.view_report);
        viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ViewReport.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });

        Button deletetask = (Button) view.findViewById(R.id.delete_report);
        deletetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DeleteReport.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
