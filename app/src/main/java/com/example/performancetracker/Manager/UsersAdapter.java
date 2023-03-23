package com.example.performancetracker.Manager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.performancetracker.R;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {
    Userlist userlist;
    Context context;
    ArrayList<UserModel> userArrayList;

    public UsersAdapter(Context context, ArrayList<UserModel> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.manager_model_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.ViewHolder holder, int position) {

        UserModel userModel = userArrayList.get(position);

        holder.name.setText(userModel.name);
        holder.phone.setText(userModel.phone);
        holder.email.setText(userModel.email);

        holder.mAssign.setOnClickListener(v -> {
//            holder.name.getText().toString();
//            holder.phone.getText().toString();
            final Intent intent = new Intent(context, AssignTask.class);

            intent.putExtra("fname", userModel.name);
            intent.putExtra("fphone", userModel.phone);
            intent.putExtra("fmail", userModel.email);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, phone, email;
        Button mAssign;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            phone = itemView.findViewById(R.id.tvPhone);
            email = itemView.findViewById(R.id.tvEmail);
            layout = itemView.findViewById(R.id.user);

            mAssign = itemView.findViewById(R.id.tvAssign);
        }
    }
}
