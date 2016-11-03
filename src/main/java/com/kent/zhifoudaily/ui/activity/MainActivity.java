package com.kent.zhifoudaily.ui.activity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.adapter.StoriesAdapter;
import com.kent.zhifoudaily.adapter.TopStoriesAdapter;
import com.kent.zhifoudaily.entity.NewsBefore;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.retrofit.ZhiHuHttpHelper;
import com.kent.zhifoudaily.ui.view.RecyclerViewListener;
import com.kent.zhifoudaily.utils.BarUtils;
import com.kent.zhifoudaily.utils.ScreenUtils;
import com.rd.PageIndicatorView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                if (mRecyclerView!=null){
                    mRecyclerView.scrollToPosition(0);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        onCreate();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final String TAG = "MainActivity";
    private ViewPager mViewPager;
    private PageIndicatorView mIndicator;
    private List<NewsLatest.TopStoriesBean> mTopStories;
    private List<NewsLatest.StoriesBean> mStories;
    private TopStoriesAdapter topAdapter;
    private StoriesAdapter storiesAdapter;
    private RecyclerView mRecyclerView;
    private SparseArray<String> mDateHeaders;
    private int dateBeforeToday;

    private void onCreate() {
        findViewById(R.id.content_main);
        setTitle("首页");
        initView();
        initDatas();
    }

    private void initView() {
        //ViewPager
        View headerLayout = LayoutInflater.from(this).inflate(R.layout.layout_header, null);
        mViewPager = (ViewPager) headerLayout.findViewById(R.id.viewPager);
        mViewPager.getLayoutParams().height = ScreenUtils.getScreenHeight(this) / 3;
        mIndicator = (PageIndicatorView) headerLayout.findViewById(R.id.pageIndicatorView);
        topAdapter = new TopStoriesAdapter(this);
        mViewPager.setAdapter(topAdapter);
        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_newest);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storiesAdapter = new StoriesAdapter(this);
        storiesAdapter.addHeaderView(headerLayout);
        mRecyclerView.setAdapter(storiesAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewListener(this));
        storiesAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNewsBefore();
            }
        });
    }

    private void initDatas() {
        mDateHeaders = new SparseArray<>();
        mDateHeaders.put(-1, "首页");
        getNewsToday();
    }

    private void getNewsToday() {
        ZhiHuHttpHelper.getInstance().getNewsLatest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe(new Subscriber<NewsLatest>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError: ", e);
                        Toast.makeText(MainActivity.this, "更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewsLatest latest) {
                        //滚动横幅
                        mTopStories = latest.getTop_stories();
                        topAdapter.setDatas(mTopStories);
                        mIndicator.setCount(mTopStories.size());
                        //最新新闻
                        mStories = new ArrayList<>();
                        String today = "今日要闻";
                        mStories.add(new NewsLatest.StoriesBean(today, true));
                        List<NewsLatest.StoriesBean> stories = latest.getStories();
                        for (NewsLatest.StoriesBean story : stories) {
                            story.setHeaderDate(today);
                        }
                        mStories.addAll(stories);
                        storiesAdapter.addData(mStories);
                    }
                });
    }

    private void getNewsBefore() {
        ZhiHuHttpHelper.getInstance().getNewsBefore(dateBeforeToday)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {

                    }
                })
                .subscribe(new Subscriber<NewsBefore>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError: ", e);
                        Toast.makeText(MainActivity.this, "更新失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(NewsBefore newsBefore) {
                        addNewsBefore(newsBefore);
                    }
                });
    }

    @SuppressLint("SimpleDateFormat")
    private void addNewsBefore(NewsBefore newsBefore) {
        List<NewsLatest.StoriesBean> newStories = new ArrayList<>();
        String dateStr = newsBefore.getDate();
        Calendar calendar = Calendar.getInstance();
        Date date;
        String result = newsBefore.getDate();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
            calendar.setTime(date);
            result = new SimpleDateFormat("MM月dd日 " + getDayOfWeek(calendar)).format(date);
            newStories.add(new NewsLatest.StoriesBean(result, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<NewsLatest.StoriesBean> stories = newsBefore.getStories();
        if (stories.size() > 0) {
            dateBeforeToday++;
        }
        for (NewsLatest.StoriesBean story : stories) {
            story.setHeaderDate(result);
        }
        mDateHeaders.put(mStories.size() - 1, newStories.get(0).getHeaderDate());
        newStories.addAll(stories);
        mStories.addAll(newStories);
        storiesAdapter.addData(newStories);
    }

    private String getDayOfWeek(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (calendar.getFirstDayOfWeek() == Calendar.SUNDAY && --dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        if (dayOfWeek == 1) {
            return "星期一";
        } else if (dayOfWeek == 2) {
            return "星期二";
        } else if (dayOfWeek == 3) {
            return "星期三";
        } else if (dayOfWeek == 4) {
            return "星期四";
        } else if (dayOfWeek == 5) {
            return "星期五";
        } else if (dayOfWeek == 6) {
            return "星期六";
        } else if (dayOfWeek == 7) {
            return "星期天";
        } else {
            return "星期" + dayOfWeek;
        }
    }
}
