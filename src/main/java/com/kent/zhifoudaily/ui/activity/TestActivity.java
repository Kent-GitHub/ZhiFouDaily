package com.kent.zhifoudaily.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.utils.ScreenUtils;

/**
 * Created by Kent ↗↗↗ on 2016/11/1.
 */

public class TestActivity extends Activity{
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_header);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.getLayoutParams().height= ScreenUtils.getScreenHeight(this)/3;
    }
}
