package com.android.dailyduas.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dailyduas.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    ImageView duaImage;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tvNameVH);
        duaImage = itemView.findViewById(R.id.ivRecylerImage);

    }
}
