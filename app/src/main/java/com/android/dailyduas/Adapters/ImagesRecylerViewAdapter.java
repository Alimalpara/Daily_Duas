package com.android.dailyduas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.dailyduas.R;
import com.android.dailyduas.Utilties.UTILS;

import java.util.ArrayList;

public class ImagesRecylerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<String> img ;
    ArrayList<Integer> ids;

    public ImagesRecylerViewAdapter(Context context, ArrayList<String> img) {
        this.context = context;
        this.img = img;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.imagesduarecyclerview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int id =0;
        /*for(int i = 0; i< img.size();i++){
            ids = new ArrayList<>();

             id = UTILS.getImageid(img.get(i),context);
             ids.add(id);

        }*/
        ids = new ArrayList<>();
        //code to cnvert string image name to id
        for (String i :img) {

            id = UTILS.getImageid(i,context);
            ids.add(id);//new arraylist defined


        }
        holder.duaImage.setImageResource(ids.get(position));
      //  holder.duaImage.setImageResource(ids.get(position)-1);





    }

    @Override
    public int getItemCount() {
        return img.size();
    }
}
