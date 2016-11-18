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
import com.kent.zhifoudaily.entity.StoriesBean;
import com.kent.zhifoudaily.utils.ConvertUtils;
import com.kent.zhifoudaily.utils.ScreenUtils;

import java.util.List;

public class NewestStoryView extends CardView {

    private ImageView mImageView;
    private TextView mTitle, multiPic;

    private Context mContext;
    private int eightDp;

    public NewestStoryView(Context context) {
        super(context);
        mContext = context;
        eightDp = ConvertUtils.dp2px(mContext, 8);
        LayoutInflater.from(context).inflate(R.layout.layout_newest_story, this);
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        FrameLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.setMargins(eightDp, eightDp, eightDp, eightDp);
        setMinimumHeight(ConvertUtils.dp2px(mContext, 80));
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

    public void setData(StoriesBean data) {
        List<String> images = data.getImages();
        if (images != null) {
            String string = images.get(0);
            Glide.with(mContext).load(string).into(mImageView);
            mImageView.setVisibility(VISIBLE);
        } else {
            mImageView.setVisibility(GONE);
        }
        mTitle.setText(data.getTitle());
        isMultiPic(data.isMultipic());
    }
}
