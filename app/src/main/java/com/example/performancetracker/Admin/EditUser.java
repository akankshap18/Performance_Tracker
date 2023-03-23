package com.example.performancetracker.Admin;

import static com.example.performancetracker.Admin.AddUser.TAG;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.performancetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class EditUser extends AppCompatActivity {
    TextView fullName, email, phone;
    Button update;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_edit_user);
        getSupportActionBar().setTitle("Update User");

        phone = findViewById(R.id.mPhone);
        fullName = findViewById(R.id.mName);
        email = findViewById(R.id.mEmail);
        update = findViewById(R.id.mEdit);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();
        update.setOnClickListener(v -> {
            update(userId);
        });
    }
    private void update(String userId) {
        fstore.collection("users").document(userId).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(EditUser.this, "User has been updated from Database.", Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fstore.collection("users").document(userId);
                Map<String, Object> user = new HashMap<>();
                fullName.setText("");
                email.setText("");
                phone.setText("");

                documentReference.set(user).addOnSuccessListener(aVoid -> Log.d
                                (TAG, "onSuccess: User Profile is updated for " + userId))
                        .addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                startActivity(new Intent(getApplicationContext(), Admin.class));
            } else {
                Toast.makeText(EditUser.this, "Fail to update the user. ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}