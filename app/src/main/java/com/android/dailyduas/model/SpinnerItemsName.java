package com.android.dailyduas.model;

import java.util.ArrayList;
import java.util.List;

public class SpinnerItemsName {
    public static ArrayList<FontName> getFontName(){
        ArrayList<FontName> fontNames = new ArrayList<>();
        FontName one = new FontName();
        FontName two = new FontName();
        FontName three = new FontName();
        FontName four = new FontName();
        FontName five = new FontName();
        FontName six = new FontName();


        one.setName("Open sans Bold");
        two.setName("Open sans Regular");
        three.setName("Times New Roman");
        four.setName("Motena Golden Regular");
        five.setName("Roboto");
        six.setName("Roboto Variable");

        fontNames.add(one);
        fontNames.add(two);
        fontNames.add(three);
        fontNames.add(four);
        fontNames.add(five);
        fontNames.add(six);


        return fontNames;
    }
}
