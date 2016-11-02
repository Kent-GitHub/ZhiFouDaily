package com.kent.zhifoudaily.ui.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.utils.ScreenUtils;

/**
 * Created by Kent ↗↗↗ on 2016/11/1.
 */

public class NewestStoryView extends CardView {

    private ImageView mImageView;
    private TextView mTitle, multiPic;

    private Context mContext;

    public NewestStoryView(Context context) {
        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_newest_story, this);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        FrameLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(16,8,16,8);
        init();
    }

    private void init() {
        mImageView = (ImageView) findViewById(R.id.iv_story_list);
        mImageView.getLayoutParams().height = ScreenUtils.getScreenHeight(mContext) / 11;
        mTitle = (TextView) findViewById(R.id.tv_story_list);
        multiPic = (TextView) findViewById(R.id.tv_multiPic);
    }

    public void isMultiPic(boolean isMultiPic) {
        this.multiPic.setVisibility(isMultiPic ? VISIBLE : INVISIBLE);
    }

    public void setData(NewsLatest.StoriesBean data) {
        Glide.with(mContext).load(data.getImages().get(0)).into(mImageView);
        mTitle.setText(data.getTitle());
        isMultiPic(data.isMultipic());
    }
}
