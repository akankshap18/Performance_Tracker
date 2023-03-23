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

public class ManageMA extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_managema,container,false);
        Button addManager = (Button) view.findViewById(R.id.add_manager);
        addManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddManager.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });

        Button deleteManager = (Button) view.findViewById(R.id.delete_manager);
        deleteManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DeleteManager.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });

        Button addUser = (Button) view.findViewById(R.id.add_users);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddUser.class);
                i.putExtra("some", "some data");
                startActivity(i);
            }
        });

        Button deleteUser = (Button) view.findViewById(R.id.delete_users);
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DeleteUser.class);
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
