package com.example.performancetracker.Manager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {
    private String TAG = "TAG";
    EditText Task, Description;
    Button Add;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_add_task);
        getSupportActionBar().setTitle("Add Task");

        Task = findViewById(R.id.mTask);
        Description = findViewById(R.id.mDescription);
        Add = findViewById(R.id.submit);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTaskToFirestore();
            }
        });
    }

    public void addTaskToFirestore() {
        Map<String, String> Tasks = new HashMap<>();
        Tasks.put("Taskname", Task.getText().toString());
        Tasks.put("TaskDescription",Description.getText().toString());
        fstore.collection("task").add(Tasks)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Task.setText("");
                    Description.setText("");
                    Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_LONG).show();
                }
        });
    }
}