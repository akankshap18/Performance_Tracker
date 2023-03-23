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

public class ManageUsers extends Fragment {
//    Toolbar toolbar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_manageusers,container,false);
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");

        Button users = (Button) view.findViewById(R.id.list_users);
        users.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(), Users.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button edit_users = (Button) view.findViewById(R.id.edit_users);
        edit_users.setOnClickListener(view12 -> {
            Intent i = new Intent(getActivity(), EditUser.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button addUser = (Button) view.findViewById(R.id.add_users);
        addUser.setOnClickListener(view13 -> {
            Intent i = new Intent(getActivity(), AddUser.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button deleteUser = (Button) view.findViewById(R.id.delete_users);
        deleteUser.setOnClickListener(view14 -> {
            Intent i = new Intent(getActivity(), DeleteUser.class);
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
