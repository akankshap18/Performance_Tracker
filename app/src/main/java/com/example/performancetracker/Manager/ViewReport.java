package com.example.performancetracker.Manager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.Admin.ReportAdapter;
import com.example.performancetracker.Admin.ReportModel;
import com.example.performancetracker.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewReport extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ReportModel> reportArrayList;
    ReportAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity_view_report);
        getSupportActionBar().setTitle("View Report");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        reportArrayList = new ArrayList<ReportModel>();
        adapter = new ReportAdapter(ViewReport.this,reportArrayList);

        recyclerView.setAdapter(adapter);

        EventChangeListener();

    }

    private void EventChangeListener() {

        db.collection("report")
                .addSnapshotListener(new EventListener<>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }

                        for (DocumentSnapshot doc : value.getDocuments()) {
                            ReportModel model = new ReportModel(doc.getString("reports"),
                                    doc.getString("done"));
                            reportArrayList.add(model);
                        }
                        adapter = new ReportAdapter(ViewReport.this, reportArrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

                });
    }
}