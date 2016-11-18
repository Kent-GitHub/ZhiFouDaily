package com.kent.zhifoudaily.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.utils.ScreenUtils;

public class ThemeHeader {
    private ImageView mImageView;
    private TextView mTextView;
    private Context mContext;
    private View mView;

    public ThemeHeader(Context context) {
        mContext = context;
        init();
    }

    private void init() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.layout_top_story, null);
        ViewGroup.LayoutParams viewLp = new LinearLayout.LayoutParams(-1, ScreenUtils.getScreenHeight(mContext) / 3);
        mView.setLayoutParams(viewLp);
        mImageView = (ImageView) mView.findViewById(R.id.iv_newest);
        mTextView = (TextView) mView.findViewById(R.id.tv_newest);
    }

    public View getView() {
        return mView;
    }

    public TextView getTextView() {
        return mTextView;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    public void setText(String text) {
        mTextView.setText(text);
    }
}
