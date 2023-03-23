package com.example.performancetracker.Admin;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.performancetracker.R;

public class MainFragment extends Fragment {
    private OnFragmentItemSelectedListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_main,container,false);
        Button clickMe = view.findViewById(R.id.clickMe);
        clickMe.setOnClickListener(v -> listener.onButtonSelected());
        Button click = view.findViewById(R.id.click);
        click.setOnClickListener(v -> listener.onButtonSelected());
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentItemSelectedListener) {
            listener = (OnFragmentItemSelectedListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + " must implement listener");
        }
    }

    public interface  OnFragmentItemSelectedListener{
        void onButtonSelected();
    }
}
