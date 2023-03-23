package com.example.performancetracker;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.Admin.Admin;
import com.example.performancetracker.Manager.Manager;
import com.example.performancetracker.User.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    Spinner mSpinner;
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    Toolbar toolbar;

    FirebaseFirestore fstore;
    String userID, managerID, adminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);
        mSpinner = findViewById(R.id.usertype);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.usertype, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

//        if(fAuth.getCurrentUser() != null) {
//            startActivity(new Intent(getApplicationContext(), Admin.class));
//            finish();
//        }

        mLoginBtn.setOnClickListener(v -> {
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
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

            fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    switch (item) {
                        case "User":
                            Toast.makeText(MainActivity.this, "User Logged in successfully", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(), Users.class));
                            break;
                        case "Manager":
                            Toast.makeText(MainActivity.this, "Manager Logged in successfully", Toast.LENGTH_SHORT).show();
                            managerID = fAuth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(), Manager.class));
                            break;
                        case "Admin":
                            Toast.makeText(MainActivity.this, "Admin Logged in successfully", Toast.LENGTH_SHORT).show();
                            adminID = fAuth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(), Admin.class));
                            break;
                    }
                }

                else {
                    Toast.makeText(MainActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            });
        });

        mCreateBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Register.class)));
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }
}
