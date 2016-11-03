package com.kent.zhifoudaily.event;

import com.kent.zhifoudaily.entity.NewsLatest;

import java.util.List;

public class ShowStories {
    private int current;
    private List<NewsLatest.StoriesBean> mStories;

    public ShowStories(List<NewsLatest.StoriesBean> mStories, int current) {
        this.mStories = mStories;
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public List<NewsLatest.StoriesBean> getStories() {
        return mStories;
    }
}
