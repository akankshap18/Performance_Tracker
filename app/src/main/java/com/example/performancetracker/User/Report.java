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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Report extends AppCompatActivity {
    private String TAG = "TAG";
    EditText mReport, doneBy;
    Button submit;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String reportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_report);
        getSupportActionBar().setTitle("Report");

        mReport = findViewById(R.id.w_report);
        doneBy = findViewById(R.id.doneby);
        submit = findViewById(R.id.submit);
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
        Map<String, String> Reports = new HashMap<>();
        Reports.put("reports", mReport.getText().toString());
        Reports.put("done", doneBy.getText().toString());
        fstore.collection("report").add(Reports)
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    mReport.setText("");
                    doneBy.setText("");
                    Toast.makeText(getApplicationContext(), "Report generated", Toast.LENGTH_LONG).show();
                }
            });
    }
}