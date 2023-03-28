package com.android.dailyduas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.dailyduas.model.Items;
import com.android.dailyduas.model.RecylerviewAdapter;
import com.android.dailyduas.model.UTILS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView main;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Items> itemsArrayList = new ArrayList<>();
    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (RecyclerView) findViewById(R.id.rvMain);


        //to get data from json
        get_json("dailyduas.json");
        main.setLayoutManager(new LinearLayoutManager(this));
        main.setAdapter(new RecylerviewAdapter(getApplicationContext(), itemsArrayList));

    }

    public void get_json(String jsonname) {

        try {
            UTILS utils = new UTILS(this);
            JSONArray jsonArray = utils.get_json(jsonname);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj != null) {
                    //created a new instance because of duplication issue
                    items = new Items();
                    items.setName(obj.getString("name"));
                    //aded items to array list to display in home page and added to recycler view
                    itemsArrayList.add(items);
                }
                // Toast.makeText(this, "inside json array", Toast.LENGTH_SHORT).show();


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}