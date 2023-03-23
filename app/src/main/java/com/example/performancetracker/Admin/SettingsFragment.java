package com.example.performancetracker.Admin;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.performancetracker.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.admin_settings, rootKey);
    }
}