package com.example.performancetracker.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.Manager.Manager;
import com.example.performancetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DeleteManager extends AppCompatActivity {
    EditText fullName, email, phone;
    Button delete;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String managerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_delete_manager);
        getSupportActionBar().setTitle("Delete Manager");

        Manager manager = (Manager) getIntent().getSerializableExtra("managers");
        fullName = findViewById(R.id.mName);
        phone = findViewById(R.id.mPhone);
        email = findViewById(R.id.mEmail);
        delete = findViewById(R.id.mDelete);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        managerId = fAuth.getCurrentUser().getUid();
        delete.setOnClickListener(v -> {
            delete(managerId);
        });
        fullName.getText().toString();
        phone.getText().toString();
        email.getText().toString();
    }

    private void delete(String userId) {
        fstore.collection("managers").document(managerId).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(DeleteManager.this, "Manager has been deleted from Database.", Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fstore.collection("users").document(managerId);
                Map<String, Object> user = new HashMap<>();
                fullName.setText("");
                email.setText("");
                phone.setText("");
                String TAG = "";
                documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: Manager Profile is deleted for " + managerId)).addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                startActivity(new Intent(getApplicationContext(), Admin.class));
            } else {
                Toast.makeText(DeleteManager.this, "Fail to delete the manager. ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}