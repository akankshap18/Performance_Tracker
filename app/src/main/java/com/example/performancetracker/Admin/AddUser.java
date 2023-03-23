package com.example.performancetracker.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddUser extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mAddBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String fname, uphone, uemail;
    ProgressDialog pd;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_add_user);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Add User");

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mAddBtn = findViewById(R.id.add);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            fname = bundle.getString("fname");
            uphone = bundle.getString("uphone");
            uemail = bundle.getString("uemail");
            mFullName.setText(fname);
            mPhone.setText(uphone);
            mEmail.setText(uemail);
        }
        else
        {
            actionBar.setTitle("Add Data");
            mAddBtn.setText("Save");
        }

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        mAddBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String fullName = mFullName.getText().toString();
            String phone = mPhone.getText().toString();

            Bundle bundle1 = getIntent().getExtras();
            if(bundle != null) {
                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                String FullName = mFullName.getText().toString();
                String Phone = mPhone.getText().toString();
                updateData(fullName, Password, Email, Phone);
            }
            else {
                String Email = mEmail.getText().toString().trim();
                String Password = mPassword.getText().toString().trim();
                String FullName = mFullName.getText().toString();
                String Phone = mPhone.getText().toString();
                uploadData(fullName, Password, Email, Phone);
            }
        });
    }

    private void updateData(String fullName, String password, String email, String phone) {
        fstore.collection("users").document(userID)
                .update("fName", fullName, "phone", phone, "email", email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(AddUser.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(AddUser.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String fullName, String password, String email, String phone) {
        fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(AddUser.this, "User Created", Toast.LENGTH_SHORT).show();
                userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("fName", fullName);
                user.put("email", email);
                user.put("phone", phone);
                user.put("password", password);
                documentReference.set(user).addOnSuccessListener(aVoid -> Log.d(TAG, "onSuccess: User Profile is created for " + userID)).addOnFailureListener(e -> Log.d(TAG, "onFailure: " + e));
                startActivity(new Intent(getApplicationContext(), Admin.class));
            }
        });
    }
}