package com.example.performancetracker.Manager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Collections;

public class Userlist extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<UserModel> usersArrayList;
    UsersAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_users);
        getSupportActionBar().setTitle("Users List");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        usersArrayList = new ArrayList<UserModel>();
        adapter = new UsersAdapter(Userlist.this,usersArrayList);

        recyclerView.setAdapter(adapter);

        EventChangeListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_aToz:
                Collections.sort(usersArrayList, UserModel.modelAtoZComparator);
                Toast.makeText(Userlist.this, "Sort A to Z", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_zToa:
                Collections.sort(usersArrayList, UserModel.modelZtoAComparator);
                Toast.makeText(Userlist.this, "Sort Z to A", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_asc:
                Toast.makeText(Userlist.this, "Sort Date Ascending", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_desc:
                Toast.makeText(Userlist.this, "Sort Date Descending", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void EventChangeListener() {

        db.collection("users")
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
                            UserModel userModel = new UserModel(doc.getString("fName"),
                                    doc.getString("phone"), doc.getString("email"));
                            usersArrayList.add(userModel);
                        }
                        adapter = new UsersAdapter(Userlist.this, usersArrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
        });
    }
}
