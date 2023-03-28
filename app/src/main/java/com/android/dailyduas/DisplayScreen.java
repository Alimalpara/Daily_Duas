package com.android.dailyduas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dailyduas.model.SpinnerAdapter;
import com.android.dailyduas.model.SpinnerItemsName;
import com.android.dailyduas.model.UTILS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.Objects;

public class DisplayScreen extends AppCompatActivity {
    TextView content;
    String name;
    SeekBar seekBar;
    Spinner spinner;
    String spinnerItemSelected;
    SpinnerAdapter spinnerAdapter;
    ConstraintLayout fonstyleLayout;
    ImageView duasImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_screen);
        setTitle("Daily Duas");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initViews();
        //to get json file
        get_json("dailyduas.json");
        //to set default text of open sans bold.
        changefont("0");
        //duasImages.setImageResource(R.drawable.afterwuzu1);

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

                hideMenuItems(seekBar);


            }
        });
        //spinner methods
        spinnerAdapter = new SpinnerAdapter(this, SpinnerItemsName.getFontName());
        spinner.setAdapter(spinnerAdapter);
        /*ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,R.array.fonts, android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);*/


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                spinnerItemSelected = parent.getSelectedItem().toString();




                Toast.makeText(DisplayScreen.this, spinnerItemSelected, Toast.LENGTH_SHORT).show();
                changefont(spinnerItemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerItemSelected = parent.getItemAtPosition(0).toString();
                changefont(spinnerItemSelected);


                hideMenuItems(fonstyleLayout);



            }


        });


    }

    public void get_json(String jsonname) {


        try {
            UTILS utils = new UTILS(this);
            JSONArray jsonArray = utils.get_json(jsonname);
            //Toast.makeText(this, jsonArray.length(), Toast.LENGTH_SHORT).show();


//to get single json objects
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                //to check for boolean and images exists or not
                if (obj != null && obj.getString("name").equals(name)) {
                    //if no images
                    if (!obj.getBoolean("isimages")) {
                        content.setText(obj.getString("content"));
                        duasImages.setVisibility(View.GONE);
                    } else if (obj.getBoolean("isimages")) {
                        //if images are there
//                        JSONObject imageObj = obj.getJSONObject("images");
//                        JSONArray imageArray = obj.getJSONArray("images");
                        Context context = this; // context
                        Resources resources = context.getResources();
                        String imgname = obj.getString("image");
                        // get resource id by image name
                        final int resourceId = resources.getIdentifier(imgname, "drawable", context.getPackageName());
                        duasImages.setImageResource(resourceId);

                        content.setText("Images here //");
                        obj.getString("images");
                        JSONArray imagesArray = obj.getJSONArray("images");

                        //int lenth = imagesArray.length();
                       /// Toast.makeText(context, lenth, Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(context, imagesArray.length(), Toast.LENGTH_SHORT).show();

                        for(int j = 0; j<imagesArray.length();j++){
                            JSONObject imagesObj = imagesArray.getJSONObject(j);
                            Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
                            if(imagesObj!=null){
                                String name = imagesObj.getString("images");
                                Toast.makeText(this, imagesObj.length(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }

                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void initViews() {
        content = (TextView) findViewById(R.id.tvcontent);
        //to get name from intent
        name = getIntent().getStringExtra("name");
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        spinner = (Spinner) findViewById(R.id.spinnerContent);
        fonstyleLayout = (ConstraintLayout) findViewById(R.id.fontStylelayout);
        duasImages = (ImageView) findViewById(R.id.ivDuas);


    }

    //ovveride menu and creqate a menu option

    //menu methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contentmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //to get id for particular item
        int id = item.getItemId();

        switch (id) {
            case R.id.mchangeFontSize:
                //Toast.makeText(this, "helo", Toast.LENGTH_SHORT).show();
                seekBar.setVisibility(View.VISIBLE);
                //      to hide the seek bar after 10 seconds if not used
                hideMenuItems(seekBar);

                break;
            //second case
            case R.id.mchangeFontStyle:
                fonstyleLayout.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);

                hideMenuItems(fonstyleLayout);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //to hide menu items

    public void hideMenuItems(View view) {
        final Handler handlermain2 = new Handler();
        handlermain2.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view.getVisibility() == View.VISIBLE) {
                    Toast.makeText(DisplayScreen.this, "menu hide run", Toast.LENGTH_SHORT).show();
                    if(view==fonstyleLayout){
                       hideSpinnerDropDown(spinner);

                    }

                    view.setVisibility(View.GONE);



                }

            }
        }, 10000);


    }




    //to change font
    public void changefont(String font) {
        switch (font) {
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

   //to hide spinner dropdown when nothing is selected
   public  void hideSpinnerDropDown(Spinner spinner) {
       try {
           Method method = Spinner.class.getDeclaredMethod("onDetachedFromWindow");
           method.setAccessible(true);
           method.invoke(spinner);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}