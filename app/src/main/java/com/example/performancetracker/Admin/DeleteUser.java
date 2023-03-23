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

public class DeleteUser extends AppCompatActivity {
    EditText fullName, email, phone;
    Button delete;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_delete_user);
        getSupportActionBar().setTitle("Delete User");

        Users user = (Users) getIntent().getSerializableExtra("users");
        fullName = findViewById(R.id.mName);
        phone = findViewById(R.id.mPhone);
        email = findViewById(R.id.mEmail);
        delete = findViewById(R.id.mDelete);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        delete.setOnClickListener(v -> {
            delete(userId);
        });
            fullName.getText().toString();
            phone.getText().toString();
            email.getText().toString();
    }


    private void delete(String userId) {
        fstore.collection("users").document(userId).delete().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                        Toast.makeText(DeleteUser.this, "User has been deleted from Database.", Toast.LENGTH_SHORT).show();
                        DocumentReference documentReference = fstore.collection("users").document(userId);
                        Map<String, Object> user = new HashMap<>();
                        fullName.setText("");
                        email.setText("");
                        phone.setText("");
                        String TAG = "";
                        documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: User Profile is deleted for " + userId)).addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                        startActivity(new Intent(getApplicationContext(), Admin.class));
                } else {
                        Toast.makeText(DeleteUser.this, "Fail to delete the user. ", Toast.LENGTH_SHORT).show();
                }
        });
    }
}
