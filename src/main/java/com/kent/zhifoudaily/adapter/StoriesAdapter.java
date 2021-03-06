package com.kent.zhifoudaily.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kent.zhifoudaily.entity.AttrsValueHolder;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StoriesBean;
import com.kent.zhifoudaily.event.ToggleNightMode;
import com.kent.zhifoudaily.ui.view.NewestStoryView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class StoriesAdapter extends BaseQuickAdapter<StoriesBean, BaseViewHolder> {
    private Context mContext;
    private static final int TypeDate = 0x001;
    private static final int TypeNormal = 0x002;

    public StoriesAdapter(Context mContext) {
        super(new ArrayList<StoriesBean>());
        this.mContext = mContext;
    }

    public StoriesAdapter(int layoutResId, List<StoriesBean> data, Context mContext) {
        super(layoutResId, data);
        this.mContext = mContext;

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StoriesBean storiesBean) {
        if (!storiesBean.isHeaderDate()) {
            ((NewestStoryView) baseViewHolder.getConvertView()).setData(storiesBean, mode);
        } else {
            ((TextView) baseViewHolder.getConvertView()).setText(storiesBean.getHeaderDate());
            if (mode != null) ((TextView) baseViewHolder.getConvertView()).setTextColor(mode.attrs.textColorLight);
        }
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TypeNormal) {
            return new BaseViewHolder(new NewestStoryView(mContext));
        } else {
            TextView date = new TextView(mContext);
            // FIXME: 2016/11/19 fixSize
            date.setPadding(32, 32, 32, 32);
            return new BaseViewHolder(date);
        }
    }

    private int lastDatePosition;
    private String lastDate;

    @Override
    protected int getDefItemViewType(int position) {
        if (!getData().get(position).isHeaderDate()) {
            return TypeNormal;
        } else {
            return TypeDate;
        }
    }

    private NewsDateChangeListener mNewsDateChangeListener;

    public interface NewsDateChangeListener {
        void dateChange(String date);
    }

    public void setNewsDateChangeListener(NewsDateChangeListener listener) {
        mNewsDateChangeListener = listener;
    }

    private ToggleNightMode mode;

    @Subscribe
    public void onNightModeChange(ToggleNightMode mode) {
        this.mode = mode;
    }
}
