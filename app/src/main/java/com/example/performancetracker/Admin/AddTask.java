package com.example.performancetracker.Admin;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.performancetracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddTask extends AppCompatActivity {
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String TAG = "TAG";
    EditText Task, Description;
    Button Add;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_add_task);
        getSupportActionBar().setTitle("Add Task");

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        Task = findViewById(R.id.mTask);
        Description = findViewById(R.id.mDescription);
        Add = findViewById(R.id.submit);
        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        Add.setOnClickListener(v -> addTaskToFirestore());

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
    }

    public void addTaskToFirestore() {
        Map<String, String> Tasks = new HashMap<>();
        Tasks.put("Taskname", Task.getText().toString());
        Tasks.put("TaskDescription",Description.getText().toString());
        Tasks.put("DueDate", mDisplayDate.getText().toString());
        fstore.collection("task").add(Tasks)
                .addOnCompleteListener(task -> {
                    Task.setText("");
                    Description.setText("");
                    mDisplayDate.setText("");
                    Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_LONG).show();
        });
    }
}



