package com.android.dailyduas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.android.dailyduas.model.Items;
import com.android.dailyduas.Adapters.DisplayRecylerviewAdapter;
import com.android.dailyduas.Utilties.UTILS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView main;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<Items> itemsArrayList = new ArrayList<>();
    Items items;
    FirebaseRemoteConfig
            mFirebaseRemoteConfig;
    DisplayRecylerviewAdapter displayRecylerviewAdapter;
    int count = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = (RecyclerView) findViewById(R.id.rvMain);


        //to get data from json
        //get_json("dailyduas.json");
        //here firebasejson method is used to minimize the firebase content
        /*and in it is the method for oncomplete and getting the json from online and setting it to a valuue
                after that the value is passed to the function getjson in which all the functions and implemnetations are done
                and then the adapter for recyclerview and other are set in get json
                so heirarchy is firebasejson->get json from online->pass that string to getjson->
                aall other functions are done in getjson.*/



        fireBaseJson();

    }

    //to get from firebase
    public void fireBaseJson(){
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        //tofetch and activate
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if(task.isSuccessful()){
                    String jsononline = mFirebaseRemoteConfig.getString("dailyduas1");
                   /*   JSONArray jsonArray = new JSONArray(jsononline);
                      for (int i = 0; i<jsonArray.length();i++){

                          JSONObject obj = jsonArray.getJSONObject(i);
                          if (obj != null) {
                              //created a new instance because of duplication issue
                              items = new Items();
                              items.setName(obj.getString("name"));
                              //aded items to array list to display in home page and added to recycler view
                              itemsArrayList.add(items);
                          }
                      }*/
                    get_json(jsononline);


                }

            }
        });
    }


    public void get_json(String jsonname) {

        try {
            count++;
          /*  UTILS utils = new UTILS(this);
            JSONArray jsonArray = utils.get_json(jsonname);*/
            JSONArray jsonArray = new JSONArray(jsonname);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj != null) {
                    //created a new instance because of duplication issue
                    items = new Items();
                    items.setName(obj.getString("name"));
                    //aded items to array list to display in home page and added to recycler view
                    itemsArrayList.add(items);
                }



            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Count out"+count, Toast.LENGTH_SHORT).show();

        // Toast.makeText(this, "inside json array", Toast.LENGTH_SHORT).show();
        main.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        displayRecylerviewAdapter = new DisplayRecylerviewAdapter(getApplicationContext(),itemsArrayList);
        displayRecylerviewAdapter.notifyDataSetChanged();

        main.setAdapter(displayRecylerviewAdapter);
    }
}