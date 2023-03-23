package com.example.performancetracker.Admin;

import android.content.Intent;
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

public class CreateLead extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPhone;
    Button mCreateBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_create_lead);
        getSupportActionBar().setTitle("Create Lead");

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPhone = findViewById(R.id.phone);
        mCreateBtn = findViewById(R.id.create);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataToFirestore();
            }
        });
    }

    public void addDataToFirestore() {
        Map<String, String> Lead = new HashMap<>();
        Lead.put("fName", mFullName.getText().toString());
        Lead.put("email", mEmail.getText().toString());
        Lead.put("phone", mPhone.getText().toString());
        fstore.collection("lead").add(Lead)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Lead.put("fName", String.valueOf(mFullName));
                        Lead.put("email", String.valueOf(mEmail));
                        Lead.put("phone", String.valueOf(mPhone));
                        Toast.makeText(getApplicationContext(), "Lead Created", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Admin.class));
                }
       });
    }
}
