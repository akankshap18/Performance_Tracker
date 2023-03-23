package com.example.performancetracker.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;

public class Users extends AppCompatActivity {
    ImageButton profile, task, report;
    TextView uprofile, utask, ureport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_users);

        profile = findViewById(R.id.profile);
        task = findViewById(R.id.task);
        report = findViewById(R.id.report);
        uprofile = findViewById(R.id.mProfile);
        utask = findViewById(R.id.mTask);
        ureport = findViewById(R.id.mReport);
        getSupportActionBar().setTitle("User");

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
        uprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Task.class));
            }
        });

        utask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Task.class));
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Report.class));
            }
        });

        ureport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Report.class));
            }
        });

    }
}