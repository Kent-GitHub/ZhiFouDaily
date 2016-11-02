package com.kent.zhifoudaily.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.entity.NewsLatest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kent ↗↗↗ on 2016/11/1.
 */

public class TopStoriesAdapter extends PagerAdapter {
    private Context mContext;
    private List<NewsLatest.TopStoriesBean> mTopStories;

    public TopStoriesAdapter(Context context) {
        this(context, new ArrayList<NewsLatest.TopStoriesBean>());
    }

    public TopStoriesAdapter(Context context, List<NewsLatest.TopStoriesBean> datas) {
        mContext = context;
        mTopStories = datas;
    }

    public void setDatas(List<NewsLatest.TopStoriesBean> datas) {
        mTopStories = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mTopStories.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        NewsLatest.TopStoriesBean story = mTopStories.get(position);
        ViewGroup view = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.layout_top_story, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_newest);
        TextView tv = (TextView) view.findViewById(R.id.tv_newest);
        Glide.with(mContext).load(story.getImage()).into(iv);
        tv.setText(story.getTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
