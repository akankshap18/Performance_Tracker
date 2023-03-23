package com.example.performancetracker.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DeleteTask extends AppCompatActivity {
    EditText taskName, taskDescription;
    Button delete;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String taskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_delete_task);
        getSupportActionBar().setTitle("Delete Task");

        taskName = findViewById(R.id.mName);
        taskDescription = findViewById(R.id.mDescription);
        delete = findViewById(R.id.mDelete);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        taskId = fAuth.getCurrentUser().getUid();
        delete.setOnClickListener(v -> {
            delete(taskId);
        });
        taskName.getText().toString();
        taskDescription.getText().toString();
    }


    private void delete(String taskId) {
        fstore.collection("task").document(taskId).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(DeleteTask.this, "Task has been deleted from Database.", Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fstore.collection("task").document(taskId);
                Map<String, Object> tasks = new HashMap<>();
                taskName.setText("");
                taskDescription.setText("");
                String TAG = "";
                documentReference.set(tasks).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: Task is deleted for " + taskId))
                        .addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                startActivity(new Intent(getApplicationContext(), Admin.class));
            } else {
                Toast.makeText(DeleteTask.this, "Fail to delete the task. ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}