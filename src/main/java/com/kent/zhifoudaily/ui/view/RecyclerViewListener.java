package com.kent.zhifoudaily.ui.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kent.zhifoudaily.adapter.StoriesAdapter;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewListener extends RecyclerView.OnScrollListener {
    private int lastPosition;
    private List<String> mDates;

    private MainActivity activity;

    public RecyclerViewListener(Activity activity) {
        this.activity = (MainActivity) activity;
        mDates = new ArrayList<>();
        mDates.add("首页");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        StoriesAdapter mAdapter = (StoriesAdapter) recyclerView.getAdapter();
        int position = layoutManager.findFirstVisibleItemPosition() - mAdapter.getHeaderLayoutCount();
        List<NewsLatest.StoriesBean> data = mAdapter.getData();
        if (data.size() == 0 ) return;
        if (position<0){
            activity.setTitle("首页");
        }else {
            activity.setTitle(data.get(position).getHeaderDate());
        }

    }
}
