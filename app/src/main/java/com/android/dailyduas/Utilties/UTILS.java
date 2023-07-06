package com.android.dailyduas.Utilties;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.dailyduas.Adapters.DisplayRecylerviewAdapter;
import com.android.dailyduas.DisplayScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class UTILS {
      Context context;
    FirebaseRemoteConfig
            mFirebaseRemoteConfig;
    String  jsononline;
String returnjson;

    public String getReturnjson() {
        return returnjson;
    }

    public void setReturnjson(String returnjson) {
        this.returnjson = returnjson;
    }

    public UTILS(Context context) {
        this.context = context;
    }


    //to get jsonarray statically

    public  JSONArray get_json(String filename) {


        String json;


        JSONObject obj = null;
        JSONArray jsonArray = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();


            json = new String(buffer, "UTF-8");
            jsonArray = new JSONArray(json);




        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;

    }

    //to get json string.
    public void getjsonfromFirebase(){


        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if(task.isSuccessful()){
                    jsononline = mFirebaseRemoteConfig.getString("dailyduas1");
                    setReturnjson(jsononline);

                    Toast.makeText(context, "jsononlinenot null", Toast.LENGTH_SHORT).show();




                }


            }
        });


    }
    public void setsting(){
        String a = getReturnjson();
        if(a!=null){
            Toast.makeText(context, "Got it", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Not it", Toast.LENGTH_SHORT).show();
        }
    }


    public static int getImageid(String name,Context context){
        // context
        Resources resources = context.getResources();

        // get resource id by image name
        final int resourceId = resources.getIdentifier(name, "drawable", context.getPackageName());
        return resourceId;


    }


}

