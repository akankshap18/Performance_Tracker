package com.example.performancetracker.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ViewTask extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<TaskModel> taskArrayList;
    TaskAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_view_task);
        getSupportActionBar().setTitle("View Task");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        taskArrayList = new ArrayList<>();
        adapter = new TaskAdapter(ViewTask.this,taskArrayList);

        recyclerView.setAdapter(adapter);
        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("task")
                .addSnapshotListener((value, error) -> {

                    if (error != null) {
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Log.e("Firestore error", error.getMessage());
                        return;
                    }

                    assert value != null;
                    for (DocumentSnapshot doc : value.getDocuments()) {
                        TaskModel model = new TaskModel(doc.getString("Taskname"),
                                doc.getString("TaskDescription"));
                        taskArrayList.add(model);
                    }

                    adapter = new TaskAdapter(ViewTask.this, taskArrayList);
                    recyclerView.setAdapter(adapter);

                    adapter.notifyDataSetChanged();
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                });
    }
}
