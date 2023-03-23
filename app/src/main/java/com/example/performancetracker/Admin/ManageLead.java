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

public class ManageLead extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_managelead,container,false);
        Button importLead = (Button) view.findViewById(R.id.importLead);
        importLead.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(), ImportLead.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button createlead = (Button) view.findViewById(R.id.createLead);
        createlead.setOnClickListener(view12 -> {
            Intent i = new Intent(getActivity(), CreateLead.class);
            i.putExtra("some", "some data");
            startActivity(i);
        });

        Button users = (Button) view.findViewById(R.id.users);
            users.setOnClickListener(view13 -> {
                Intent i = new Intent(getActivity(), Users.class);
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
