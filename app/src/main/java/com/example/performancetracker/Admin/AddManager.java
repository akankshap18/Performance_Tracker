package com.example.performancetracker.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.Objects;

public class AddManager extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mAddBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String managerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_add_manager);
        getSupportActionBar().setTitle("Add Manager");

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mAddBtn = findViewById(R.id.add);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        mAddBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String fullName = mFullName.getText().toString();
            String phone = mPhone.getText().toString();

            if (TextUtils.isEmpty(email)) {
                mEmail.setError("Email is required");
            }
            if (TextUtils.isEmpty(password)) {
                mPassword.setError("Password is required");
            }
            if (password.length() < 6) {
                mPassword.setError("Password must be >= 6 characters");
                return;
            }

            fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(AddManager.this, "Manager Created", Toast.LENGTH_SHORT).show();
                    managerID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                    DocumentReference documentReference = fstore.collection("managers").document(managerID);
                    Map<String, Object> Manager = new HashMap<>();
                    Manager.put("fName", fullName);
                    Manager.put("email", email);
                    Manager.put("phone", phone);
                    Manager.put("password", password);
                    documentReference.set(Manager).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: Manager Profile is created for " + managerID)).addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                    startActivity(new Intent(getApplicationContext(), Admin.class));
                }
            });
        });
    }
}