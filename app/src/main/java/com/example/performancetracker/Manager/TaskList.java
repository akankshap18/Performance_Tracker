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

import com.example.performancetracker.R;

public class TaskList extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manager_fragment_tasklist,container,false);
//        Button viewTask = (Button) view.findViewById(R.id.clickMe);
//        viewTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ViewTask.class);
//                i.putExtra("some", "some data");
//                startActivity(i);
//            }
//        });
        Button viewTask = (Button) view.findViewById(R.id.clickMe);
        viewTask.setOnClickListener(view13 -> {
            Intent i = new Intent(getActivity(), ViewTask.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button viewUsers = (Button) view.findViewById(R.id.users_list);
        viewUsers.setOnClickListener(view13 -> {
            Intent i = new Intent(getActivity(), Userlist.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
