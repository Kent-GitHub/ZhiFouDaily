package com.kent.zhifoudaily.event;

import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StoriesBean;

import java.util.List;

public class ShowStories {
    private int current;
    private List<StoriesBean> mStories;

    public ShowStories(List<StoriesBean> mStories, int current) {
        this.mStories = mStories;
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public List<StoriesBean> getStories() {
        return mStories;
    }
}
