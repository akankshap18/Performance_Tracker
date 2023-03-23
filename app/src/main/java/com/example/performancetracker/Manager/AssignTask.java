package com.example.performancetracker.Manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AssignTask extends AppCompatActivity {
    private static final String TAG = "UserDetails";
    TextView name, email, phone;
    EditText taskname;
    Button assign;
    String userID;
    ArrayList<String> spinnerlist;
    CollectionReference spinnerRef;
    FirebaseFirestore fstore;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity_assign_task);
        name = findViewById(R.id.mName);
        email = findViewById(R.id.mEmail);
        phone = findViewById(R.id.phoneNumber);
        taskname = findViewById(R.id.task);
        assign = findViewById(R.id.mAssign);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        spinnerlist = new ArrayList<>();

        getSupportActionBar().setTitle("Assign Task");
//        spinnerRef = FirebaseDatabase.getInstance().getReference("userlist");
//        spinnerRef = fstore.collection("userlist");

        String uname = getIntent().getStringExtra("fname");
        String uemail = getIntent().getStringExtra("fmail");
        String uphone = getIntent().getStringExtra("fphone");
        String tname = taskname.toString();
        taskname.setText(tname);
        name.setText(uname);
        phone.setText(uphone);
        email.setText(uemail);

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignTask();
            }
        });
    }

    private void assignTask() {
//        Toast.makeText(AssignTask.this, "Task Assigned", Toast.LENGTH_SHORT).show();
//        userID = fAuth.getCurrentUser().getUid();
//        DocumentReference documentReference = fstore.collection("usertask").document(userID);
//        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                name.setText(documentSnapshot.getString("name"));
//                email.setText(documentSnapshot.getString("email"));
//                phone.setText(documentSnapshot.getString("phone"));
//            }
            Map<String, String> UserTasks = new HashMap<>();
            UserTasks.put("Name", name.getText().toString());
            UserTasks.put("Email",email.getText().toString());
            UserTasks.put("Phone", phone.getText().toString());
            UserTasks.put("Task", taskname.getText().toString());
            fstore.collection("usertask").add(UserTasks)
                    .addOnCompleteListener(task -> {
                        name.setText("");
                        email.setText("");
                        phone.setText("");
                        taskname.setText("");
                        Toast.makeText(getApplicationContext(), "Task assigned", Toast.LENGTH_LONG).show();
                    });
//            Map<String,Object> usertask = new HashMap<>();
//                usertask.put("task",nam);
//                usertask.put("description",desc);
//                usertask.put("usertask",mSpinner);
//                documentReference.set(usertask).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void void) {
//                    Log.d(TAG, "onSuccess: Task assigned to "+ userID);
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Log.d(TAG, "onFailure: " + e.toString());
//                }
//            });
            startActivity(new Intent(getApplicationContext(), Userlist.class));
        }
}

