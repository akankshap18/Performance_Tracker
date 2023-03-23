package com.example.performancetracker.Admin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.performancetracker.R;

public class UserDetails extends AppCompatActivity {
    private static final String TAG = "UserDetails";
    TextView name, phone, email;
    Button call;
    static int PERMISSION_CODE= 100;
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        name = findViewById(R.id.mName);
        phone = findViewById(R.id.phoneNumber);
        email = findViewById(R.id.mEmail);
        call = findViewById(R.id.mCall);

        getSupportActionBar().setTitle("User Details");
        String uname = getIntent().getStringExtra("fname");
        String uemail = getIntent().getStringExtra("fmail");
        String uphone = getIntent().getStringExtra("fphone");
        name.setText(uname);
        phone.setText(uphone);
        email.setText(uemail);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String number = phone.getText().toString();
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" +number));
//                startActivity(callIntent);
                makePhoneCall();
//                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });
    }

    private void makePhoneCall() {
        String number = phone.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(UserDetails.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UserDetails.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
//
        }
//        else {
//            Toast.makeText(UserDetails.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

}
