package com.example.performancetracker.Admin;

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

public class Users extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> userArrayList;
    UserAdapter adapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_users);
        getSupportActionBar().setTitle("Userlist List");

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<Model>();
        adapter = new UserAdapter(Users.this,userArrayList);

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
                Collections.sort(userArrayList, Model.modelAtoZComparator);
                Toast.makeText(Users.this, "Sort A to Z", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_zToa:
                Collections.sort(userArrayList, Model.modelZtoAComparator);
                Toast.makeText(Users.this, "Sort Z to A", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true;

            case R.id.menu_asc:
                Toast.makeText(Users.this, "Sort Date Ascending", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menu_desc:
                Toast.makeText(Users.this, "Sort Date Descending", Toast.LENGTH_SHORT).show();
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
                            Model model = new Model(doc.getString("fName"),
                                    doc.getString("phone"), doc.getString("email"));
                            userArrayList.add(model);
                        }
                        adapter = new UserAdapter(Users.this, userArrayList);
                        recyclerView.setAdapter(adapter);

                        adapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
        });
    }
}
