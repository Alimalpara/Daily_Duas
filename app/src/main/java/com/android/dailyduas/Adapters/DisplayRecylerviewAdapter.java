package com.android.dailyduas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dailyduas.DisplayScreen;
import com.android.dailyduas.R;
import com.android.dailyduas.model.Items;

import java.util.ArrayList;

public class DisplayRecylerviewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    @NonNull
    Context context;
    ArrayList<Items> name;

    public DisplayRecylerviewAdapter(@NonNull Context context, ArrayList<Items> names) {
        this.context = context;
        this.name = names;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rvmainlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //here we will get name from arraylist from main activity
        holder.name.setText(name.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DisplayScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name", name.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

}
