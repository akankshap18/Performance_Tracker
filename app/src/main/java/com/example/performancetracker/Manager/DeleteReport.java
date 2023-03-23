package com.example.performancetracker.Manager;

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

public class DeleteReport extends AppCompatActivity {
    EditText pname, doneby;
    Button delete;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity_delete_report);
        getSupportActionBar().setTitle("Delete Report");

        getIntent().getSerializableExtra("report");
        pname = findViewById(R.id.mReport);
        doneby = findViewById(R.id.mDone);
        delete = findViewById(R.id.mDelete);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        reportId = fAuth.getCurrentUser().getUid();
        delete.setOnClickListener(v -> {
            delete(reportId);
        });
        pname.getText().toString();
        doneby.getText().toString();
    }


    private void delete(String reportId) {
        fstore.collection("report").document(reportId).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(DeleteReport.this, "Report has been deleted from Database.", Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fstore.collection("report").document(reportId);
                Map<String, Object> report = new HashMap<>();
                pname.setText("");
                doneby.setText("");
                String TAG = "";
                documentReference.set(report).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: Report is deleted for " + reportId)).
                        addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                startActivity(new Intent(getApplicationContext(), Reportees.class));
            } else {
                Toast.makeText(DeleteReport.this, "Fail to delete the report. ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
