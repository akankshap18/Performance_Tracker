package com.example.performancetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPhone;
    Spinner mSpinner;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fstore;
    String userID, managerID, adminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        mSpinner = findViewById(R.id.usertype);
        getSupportActionBar().setTitle("Register");

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progressBar);

//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//            finish();
//        }

        mRegisterBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            String fullName = mFullName.getText().toString();
            String phone = mPhone.getText().toString();
            String item = mSpinner.getSelectedItem().toString();

            if(TextUtils.isEmpty(email)){
                mEmail.setError("Email is required");
            }
            if(TextUtils.isEmpty(password)){
                mPassword.setError("Password is required");
            }
            if(password.length() < 6) {
                mPassword.setError("Password must be >= 6 characters");
                return;
            }
            if(TextUtils.isEmpty(item)){
                mSpinner.setPrompt("Select one");
            }

            progressBar.setVisibility(View.VISIBLE);
            fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    switch (item) {
                        case "User":
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("fName",fullName);
                            user.put("email",email);
                            user.put("phone",phone);
                            user.put("password", password);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            break;
                        case "Manager":
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            managerID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference1 = fstore.collection("managers").document(managerID);
                            Map<String,Object> Manager = new HashMap<>();
                            Manager.put("fName",fullName);
                            Manager.put("email",email);
                            Manager.put("phone",phone);
                            Manager.put("password", password);
                            documentReference1.set(Manager).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ managerID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            break;
                        case "Admin":
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            adminID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference2 = fstore.collection("admins").document(adminID);
                            Map<String,Object> Admin = new HashMap<>();
                            Admin.put("fName",fullName);
                            Admin.put("email",email);
                            Admin.put("phone",phone);
                            Admin.put("password", password);
                            documentReference2.set(Admin).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ adminID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            break;
                    }
                }
                else {
                    Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        mLoginBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }
}
