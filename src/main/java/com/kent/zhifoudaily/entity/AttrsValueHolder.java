package com.kent.zhifoudaily.entity;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import com.kent.zhifoudaily.R;

public class AttrsValueHolder {
    private static final String TAG = "AttrsValueHolder";
    public int textColorDark;
    public int textColorLight;
    public int backGroundColor;
    public int cvBackGroundColor;
    public int navBarCenter;
    public int navBarEnd;
    public int navBarStart;
    public int colorPrimary;
    public int colorPrimaryDark;
    public int colorAccent;

    public AttrsValueHolder(Context context) {
        int[] attrsArray = {R.attr.modeTextColorDark, R.attr.modeTextColorLight,
                R.attr.modeBackGroundColor, R.attr.modeCvBackGroundColor,
                R.attr.navBarColorCenter, R.attr.navBarColorEnd, R.attr.navBarColorStart,
                R.attr.colorPrimary, R.attr.colorPrimaryDark, R.attr.colorAccent,};
        TypedArray typedArray = context.obtainStyledAttributes(attrsArray);
        textColorDark = typedArray.getColor(0, 0);
        textColorLight = typedArray.getColor(1, 0);
        backGroundColor = typedArray.getColor(2, 0);
        cvBackGroundColor = typedArray.getColor(3, 0);
        navBarCenter = typedArray.getColor(4, 0);
        navBarEnd = typedArray.getColor(5, 0);
        navBarStart = typedArray.getColor(6, 0);
        colorPrimary = typedArray.getColor(7, 0);
        colorPrimaryDark = typedArray.getColor(8, 0);
        colorAccent = typedArray.getColor(9, 0);
        typedArray.recycle();
    }
}
