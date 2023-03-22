package com.android.dailyduas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dailyduas.model.Items;
import com.android.dailyduas.model.SpinnerAdapter;
import com.android.dailyduas.model.SpinnerItemsName;
import com.android.dailyduas.model.UTILS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

public class DisplayScreen extends AppCompatActivity {
    TextView content;
    String name ;
    SeekBar seekBar;
    Spinner spinner;
    String spinnerItemSelected;
    SpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_screen);
        setTitle("Daily Duas");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initViews();
        //to get json file
        get_json();

        //seekbar method
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                content.setTextSize(Float.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(DisplayScreen.this, "onstopTrackran", Toast.LENGTH_SHORT).show();

                //to hide the seek bar after use
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        seekBar.setVisibility(View.GONE);
                    }
                }, 3000);

            }
        });
        //spinner methods
        spinnerAdapter = new SpinnerAdapter(this, SpinnerItemsName.getFontName());
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemSelected = parent.getSelectedItem().toString();
                changefont(spinnerItemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }

        });



    }

    public void get_json(){


        try {
            UTILS utils = new UTILS(this);
            JSONArray jsonArray = utils.get_json();



            for (int i = 0 ; i <jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj!=null && obj.getString("name").equals(name)){
                    content.setText(obj.getString("content"));

                }


            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void initViews(){
        content = (TextView) findViewById(R.id.tvcontent);
        name = getIntent().getStringExtra("name");
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinnerContent);

    }

    //ovveride menu and creqate a menu option

//menu methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contentmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //to get id for particular item
        int id = item.getItemId();

        switch (id){
            case  R.id.mchangeFontSize:
                //Toast.makeText(this, "helo", Toast.LENGTH_SHORT).show();
                seekBar.setVisibility(View.VISIBLE);
          //      to hide the seek bar after 10 seconds if not used
                final Handler handlermain = new Handler();
                handlermain.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(seekBar.getVisibility()==View.VISIBLE){
                            Toast.makeText(DisplayScreen.this, "menu hide run", Toast.LENGTH_SHORT).show();
                            seekBar.setVisibility(View.GONE);

                        }

                    }
                },10000);
                break;
                //second case
            case R.id.mchangeFontStyle:
                Toast.makeText(this, "double helo", Toast.LENGTH_SHORT).show();


                break;
        }

        return super.onOptionsItemSelected(item);
    }


    //to change font
    public void changefont(String font){
        switch (font){
            case "0":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.opensansbold);
                    content.setTypeface(typeface);
                }
                break;
            case "1":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.opensansregular);
                    content.setTypeface(typeface);
                }
                break;
            case "2":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.times);
                    content.setTypeface(typeface);
                }
                break;
            case "3":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.motenagoldenregular);
                    content.setTypeface(typeface);
                }
                break;
            case "4":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.robotoregluar);
                    content.setTypeface(typeface);
                }
                break;
            case "5":
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.robotovariable);
                    content.setTypeface(typeface);
                }
                break;



        }
    }





}