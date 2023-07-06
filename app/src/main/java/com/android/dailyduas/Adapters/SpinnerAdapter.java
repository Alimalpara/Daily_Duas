package com.android.dailyduas.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.dailyduas.R;
import com.android.dailyduas.model.FontName;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<FontName> fontNames;

    public SpinnerAdapter(Context context, ArrayList<FontName> fontNames) {
        this.context = context;
        this.fontNames = fontNames;
    }

    @Override
    public int getCount() {
        return fontNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.spinnerview, parent, false);

        TextView txtName = rootView.findViewById(R.id.spiinertv);

        txtName.setText(fontNames.get(position).getName());

        switch (position){
            case 0:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.opensansbold);
                    txtName.setTypeface(typeface);

                }
                break;
            case 1:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.opensansregular);
                    txtName.setTypeface(typeface);
                }
                break;
            case 2:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.times);
                    txtName.setTypeface(typeface);

                }
                break;
            case 3:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.motenagoldenregular);
                    txtName.setTypeface(typeface);
                }
                break;
            case 4:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.robotoregluar);
                    txtName.setTypeface(typeface);

                }
                break;
            case 5:
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Typeface typeface = parent.getResources().getFont(R.font.robotovariable);
                    txtName.setTypeface(typeface);
                }
                break;


        }


        return rootView;
    }
}
