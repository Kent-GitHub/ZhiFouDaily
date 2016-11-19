package com.kent.zhifoudaily.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.entity.News;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StoriesBean;
import com.kent.zhifoudaily.entity.StoryExtra;
import com.kent.zhifoudaily.event.ShowStories;
import com.kent.zhifoudaily.retrofit.ZhiHuHttpHelper;
import com.kent.zhifoudaily.utils.BarUtils;
import com.kent.zhifoudaily.utils.HtmlUtil;
import com.kent.zhifoudaily.utils.ScreenUtils;
import com.mikepenz.actionitembadge.library.ActionItemBadge;
import com.mikepenz.actionitembadge.library.utils.BadgeStyle;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends AppCompatActivity {
    private static final String TAG = "NewsDetailActivity";
    private List<StoriesBean> mStories;
    private int currentPage, newsId;
    private ActionBar mActionBar;
    private WebView mWebView;
    private ImageView mImageView;
    private TextView mTitle, mImageSource;
    private MenuItem menuComment, menuPraise, menuShare, menuFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_news_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.news_detail_toolBar));
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowTitleEnabled(true);
        }
        newsId = getIntent().getIntExtra("newsId", -1);
        requestNews(newsId);
        initView();
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.news_detail_title);
        mWebView = (WebView) findViewById(R.id.news_detail_webView);
        mImageView = (ImageView) findViewById(R.id.news_detail_image);
        int imageHeight = ScreenUtils.getScreenHeight(this) / 3 + BarUtils.getActionBarHeight2(this);
        if (Build.VERSION.SDK_INT >= 21) {
            imageHeight += BarUtils.getStatusBarHeight(this);
        }
        mImageView.getLayoutParams().height = imageHeight;
        mImageSource = (TextView) findViewById(R.id.news_detail_image_source);
    }

    public static void Lunch(Activity from, List<StoriesBean> mStories, int current) {
        Intent intent = new Intent(from, NewsDetailActivity.class);
        intent.putExtra("newsId", mStories.get(current).getId());
        from.startActivity(intent);
        EventBus.getDefault().postSticky(new ShowStories(mStories, current));
        Log.e(TAG, "Lunch: post it.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        menuShare = menu.findItem(R.id.menu_action_share);
        menuFav = menu.findItem(R.id.menu_action_fav);
        menuComment = menu.findItem(R.id.menu_action_comment);
        menuPraise = menu.findItem(R.id.menu_action_parise);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_comment:
                if (newsId == -1) return true;
                CommentActivity.Launch(this, newsId);
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Subscribe
    public void onReceiveStories(ShowStories showStories) {
        // TODO: 2016/11/5 并不能收到
        Log.e(TAG, "onReceiveStories: get stories.");
        mStories = showStories.getStories();
        currentPage = showStories.getCurrent();
        StoriesBean story = mStories.get(currentPage);
        mTitle.setText(story.getTitle());
        requestNews(story.getId());
    }

    private void requestNews(int id) {
        if (id == -1) return;
        ZhiHuHttpHelper.getInstance().getNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe(new Subscriber<News>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError: ", e);
                        Toast.makeText(NewsDetailActivity.this, "获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(News news) {
                        updateNews(news);
                    }
                });
        ZhiHuHttpHelper.getInstance().getStoryExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe(new Subscriber<StoryExtra>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(StoryExtra storyExtra) {
                        updateMenu(storyExtra);
                    }
                });
    }

    private void updateNews(News news) {
        if (news.getImage() != null) {
            Glide.with(this).load(news.getImage()).into(mImageView);
            mTitle.setText(news.getTitle());
            mImageSource.setText(news.getImage_source());
        } else {
            //findViewById(R.id.news_detail_holder).getLayoutParams().height = BarUtils.getActionBarHeight2(this)
            //        + Build.VERSION.SDK_INT >= 21 ? BarUtils.getStatusBarHeight(this) : 0;
            TextView title2 = (TextView) findViewById(R.id.news_detail_title2);
            title2.setVisibility(View.VISIBLE);
            title2.setText(news.getTitle());
        }
        String htmlData = HtmlUtil.createHtmlData(news);
        mWebView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    private void updateMenu(StoryExtra storyExtra) {
        BadgeStyle style = new BadgeStyle(BadgeStyle.Style.DEFAULT,
                R.layout.layout_badge,
                0x80888888, 0x99888888, Color.WHITE);
        BadgeStyle translucent = new BadgeStyle(BadgeStyle.Style.DEFAULT,
                com.mikepenz.actionitembadge.library.R.layout.menu_action_item_badge,
                0x00888888, 0x00888888, Color.WHITE);
        ActionItemBadge.update(this, menuFav, FontAwesome.Icon.faw_star, translucent, "");
        ActionItemBadge.update(this, menuShare, FontAwesome.Icon.faw_share, translucent, "");
        ActionItemBadge.update(this, menuComment, FontAwesome.Icon.faw_comments, style, storyExtra.getComments());
        ActionItemBadge.update(this, menuPraise, getResources().getDrawable(R.drawable.praise), style, storyExtra.getPopularity());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
