package com.example.performancetracker.Admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ImportLead extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<LeadModel> leadArrayList;
    LeadAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_import_lead);
        getSupportActionBar().setTitle("Import Lead");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        leadArrayList = new ArrayList<LeadModel>();
        adapter = new LeadAdapter(ImportLead.this,leadArrayList);

        recyclerView.setAdapter(adapter);
        EventChangeListener();
    }

    private void EventChangeListener() {

        db.collection("lead")
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
                            LeadModel model = new LeadModel(doc.getString("fName"),
                                    doc.getString("phone"), doc.getString("email"));
                            leadArrayList.add(model);
                        }
                        adapter = new LeadAdapter(ImportLead.this, leadArrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }

        });
    }
}