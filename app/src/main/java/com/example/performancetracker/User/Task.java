package com.example.performancetracker.User;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Task extends AppCompatActivity {
    private String TAG = "TAG";
    EditText mTask, module;
    Button submit;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_task);
        getSupportActionBar().setTitle("Task");

            mTask = findViewById(R.id.mTask);
            module = findViewById(R.id.mModule);
            submit = findViewById(R.id.mSubmit);
            fAuth = FirebaseAuth.getInstance();
            fstore = FirebaseFirestore.getInstance();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addDataToFirestore();
                }
            });
        }

        public void addDataToFirestore() {
            Map<String, String> Tasks = new HashMap<>();
            Tasks.put("task", mTask.getText().toString());
            Tasks.put("module", module.getText().toString());
            fstore.collection("tasksubmit").add(Tasks)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentReference> task) {
                        mTask.setText("");
                        module.setText("");
                        Toast.makeText(getApplicationContext(), "Task Submitted", Toast.LENGTH_LONG).show();
                    }
            });

    }
}
