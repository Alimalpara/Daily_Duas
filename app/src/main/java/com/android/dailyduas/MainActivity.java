package com.android.dailyduas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.dailyduas.model.Items;
import com.android.dailyduas.model.RecylerviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView main ;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Items> itemsArrayList = new ArrayList<>();
    Items items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (RecyclerView) findViewById(R.id.rvMain);



        get_json();
        main.setLayoutManager(new LinearLayoutManager(this));
       main.setAdapter(new RecylerviewAdapter(getApplicationContext(),itemsArrayList));

    }

    public void get_json(){
        String json;

//        Toast.makeText(this, "Inside get", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, "Inside get 2", Toast.LENGTH_SHORT).show();

        try {
            InputStream is = getAssets().open("dailyduas.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0 ; i <jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj!=null){
                    //created a new instance because of duplication issue
                    items = new Items();
                    items.setName(obj.getString("name"));
                    //aded items to array list
                    itemsArrayList.add(items);
                }
                Toast.makeText(this, "inside json array", Toast.LENGTH_SHORT).show();












  }



        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}