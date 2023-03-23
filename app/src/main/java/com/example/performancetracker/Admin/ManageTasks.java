package com.example.performancetracker.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.performancetracker.R;

public class ManageTasks extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_managetasks,container,false);
        Button viewTask = (Button) view.findViewById(R.id.view_task);
        viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ViewTask.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });
        Button addtask = (Button) view.findViewById(R.id.add_task);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddTask.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });
        Button deletetask = (Button) view.findViewById(R.id.delete_task);
        deletetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DeleteTask.class);
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
