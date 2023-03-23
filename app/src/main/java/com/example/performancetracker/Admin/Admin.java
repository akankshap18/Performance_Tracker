package com.example.performancetracker.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.performancetracker.MainActivity;
import com.example.performancetracker.R;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class Admin extends AppCompatActivity implements MainFragment.OnFragmentItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {
    ManageLead manageLead;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        manageLead = new ManageLead();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,manageLead);
        fragmentTransaction.commit();

    }

    @Override
    public void onButtonSelected() {
        Toast.makeText(this, "Start New Activity", Toast.LENGTH_SHORT).show();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,new ManageLead());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        closeDrawer();
        if(menuItem.getItemId() == R.id.manageLead){
            loadFragment(new MainFragment());
        }
        if(menuItem.getItemId() == R.id.manageUsers){
            loadFragment(new ManageUsers());
        }
        if(menuItem.getItemId() == R.id.manageTasks){
            loadFragment(new ManageTasks());
        }
        if(menuItem.getItemId() == R.id.manageMA){
            loadFragment(new ManageMA());
        }
        if(menuItem.getItemId() == R.id.manageReportees){
            loadFragment(new ManageReportees());
        }
        if(menuItem.getItemId() == R.id.settingsFragment){
            loadFragment(new SettingsFragment());
        }
        if(menuItem.getItemId() == R.id.logOut){
            Toast.makeText(Admin.this, "Admin Logged out successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return true;

    }

    private void loadFragment(Fragment secondFragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,secondFragment);
        fragmentTransaction.commit();
    }

    private void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }
}