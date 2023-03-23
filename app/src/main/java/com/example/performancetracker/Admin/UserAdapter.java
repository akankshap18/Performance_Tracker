package com.example.performancetracker.Admin;

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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Users users;
    Context context;
    ArrayList<Model> userArrayList;

    public UserAdapter(Context context, ArrayList<Model> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.admin_model_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        Model model = userArrayList.get(position);

        holder.name.setText(model.name);
        holder.phone.setText(model.phone);
        holder.email.setText(model.email);

        holder.mUpdate.setOnClickListener(view -> {
            final Intent intent = new Intent(context, EditUser.class);
            context.startActivity(intent);
        });

        holder.mDelete.setOnClickListener(view -> {
            final Intent intent = new Intent(context, DeleteUser.class);
            context.startActivity(intent);
        });

        holder.layout.setOnClickListener(v -> {
//            holder.name.getText().toString();
//            holder.phone.getText().toString();
            final Intent intent = new Intent(context, UserDetails.class);

            intent.putExtra("fname", model.name);
            intent.putExtra("fphone", model.phone);
            intent.putExtra("fmail", model.email);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, phone, email;
        Button mUpdate, mDelete;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            phone = itemView.findViewById(R.id.tvPhone);
            email = itemView.findViewById(R.id.tvEmail);
            layout = itemView.findViewById(R.id.user);

            mUpdate = itemView.findViewById(R.id.tvUpdate);
            mDelete = itemView.findViewById(R.id.tvDelete);
        }
    }
}
