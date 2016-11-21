package com.kent.zhifoudaily.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.adapter.GlideCircleTransform;
import com.kent.zhifoudaily.adapter.StoriesAdapter;
import com.kent.zhifoudaily.adapter.TopStoriesAdapter;
import com.kent.zhifoudaily.application.MyApplication;
import com.kent.zhifoudaily.entity.AttrsValueHolder;
import com.kent.zhifoudaily.entity.NewsBefore;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StoriesBean;
import com.kent.zhifoudaily.entity.Theme;
import com.kent.zhifoudaily.entity.Themes;
import com.kent.zhifoudaily.event.ToggleNightMode;
import com.kent.zhifoudaily.retrofit.ZhiHuHttpHelper;
import com.kent.zhifoudaily.ui.view.ThemeEditorView;
import com.kent.zhifoudaily.ui.view.ThemeHeader;
import com.kent.zhifoudaily.utils.BarUtils;
import com.kent.zhifoudaily.utils.ConvertUtils;
import com.kent.zhifoudaily.utils.ScreenUtils;
import com.rd.PageIndicatorView;

import org.greenrobot.eventbus.EventBus;

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
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRecyclerView != null) mRecyclerView.scrollToPosition(0);
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
        } else if (newsThemeCurrent != -1) {
            newsThemeSelected(-1);
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
        } else if (id == R.id.action_toggleNight) {
            toggleNightMode();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getGroupId() == 1) {
            newsThemeSelected(item.getItemId());
        } else if (item.getItemId() == R.id.nav_menu_home) {
            newsThemeSelected(-1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static final String TAG = "MainActivity";
    private View navHeader;
    private View mHeaderLayout;
    private ThemeHeader mThemeHeader;
    private PageIndicatorView mIndicator;
    private NavigationView navigationView;
    private List<NewsLatest.TopStoriesBean> mTopStories;
    private FloatingActionButton mFab;
    private List<StoriesBean> mStories;
    private Themes mThemes;
    private TopStoriesAdapter topAdapter;
    private StoriesAdapter storiesAdapter;
    private RecyclerView mRecyclerView;
    private SparseArray<String> mDateHeaders;
    private int dateBeforeToday;
    private int newsThemeCurrent = -1;
    private boolean isNightMode;

    private void onCreate() {
        findViewById(R.id.content_main);
        setTitle("首页");
        initView();
        initDatas();
        initNavigationView();
        refreshUI();
    }

    private void initView() {
        //ViewPager
        mHeaderLayout = LayoutInflater.from(this).inflate(R.layout.layout_header, null);
        ViewPager mViewPager = (ViewPager) mHeaderLayout.findViewById(R.id.viewPager);
        mViewPager.getLayoutParams().height = ScreenUtils.getScreenHeight(this) / 3;
        mIndicator = (PageIndicatorView) mHeaderLayout.findViewById(R.id.pageIndicatorView);
        topAdapter = new TopStoriesAdapter(this);
        mViewPager.setAdapter(topAdapter);
        //RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_newest);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        storiesAdapter = new StoriesAdapter(this);
        storiesAdapter.addHeaderView(mHeaderLayout);
        storiesAdapter.setOnLoadMoreListener(getLoadMoreListener());
        mRecyclerView.setAdapter(storiesAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerViewListener(this));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                NewsDetailActivity.Lunch(MainActivity.this, storiesAdapter.getData(), i);
            }
        });
        //eventBus
        EventBus.getDefault().register(storiesAdapter);
    }

    private void initDatas() {
        mDateHeaders = new SparseArray<>();
        mDateHeaders.put(-1, "首页");
        getNewsToday();
    }

    private void initNavigationView() {
        String icon = "http://pic1.zhimg.com/da8e974dc_im.jpg";
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        View headerIcon = navHeader.findViewById(R.id.nav_header_icon);
        Glide.with(this).load(icon).transform(new GlideCircleTransform(this)).into((ImageView) headerIcon);
        final Menu menu = navigationView.getMenu();
        ZhiHuHttpHelper.getInstance().getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(2)
                .subscribe(new Subscriber<Themes>() {
                               @Override
                               public void onCompleted() {

                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.w(TAG, "onError_getThemes: ", e);
                               }

                               @Override
                               public void onNext(Themes themes) {
                                   mThemes = themes;
                                   updateNavMenu(menu, mThemes);
                               }
                           }
                );
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
                        Log.w(TAG, "onError_getNewsToday: ", e);
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
                        mStories.add(new StoriesBean(today, true));
                        List<StoriesBean> stories = latest.getStories();
                        for (StoriesBean story : stories) {
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
                        Log.w(TAG, "onError_getNewsBefore: ", e);
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
        storiesAdapter.hideLoadingMore();
        List<StoriesBean> newStories = new ArrayList<>();
        String dateStr = newsBefore.getDate();
        Calendar calendar = Calendar.getInstance();
        Date date;
        String result = newsBefore.getDate();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
            calendar.setTime(date);
            result = new SimpleDateFormat("MM月dd日 " + getDayOfWeek(calendar)).format(date);
            newStories.add(new StoriesBean(result, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<StoriesBean> stories = newsBefore.getStories();
        if (stories.size() > 0) {
            dateBeforeToday++;
        }
        for (StoriesBean story : stories) {
            story.setHeaderDate(result);
        }
        mDateHeaders.put(mStories.size() - 1, newStories.get(0).getHeaderDate());
        newStories.addAll(stories);
        mStories.addAll(newStories);
        storiesAdapter.addData(newStories);
    }

    private void updateNavMenu(Menu menu, Themes themes) {
        for (int i = 0; i < themes.getOthers().size(); i++) {
            Themes.OthersBean theme = themes.getOthers().get(i);
            menu.add(1, i, i, theme.getName());
            final MenuItem menuItem = menu.getItem(i + 1);
            Glide.with(MainActivity.this)
                    .load(theme.getThumbnail())
                    .placeholder(R.drawable.menu_avatar)
                    .transform(new GlideCircleTransform(MainActivity.this))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            menuItem.setIcon(resource);
                            return false;
                        }
                    })
                    .into(ConvertUtils.dp2px(MainActivity.this, 64), ConvertUtils.dp2px(MainActivity.this, 64));
        }
    }

    private void newsThemeSelected(int themePosition) {
        newsThemeCurrent = themePosition;
        if (themePosition == -1) {
            storiesAdapter.removeAllHeaderView();
            storiesAdapter.addHeaderView(mHeaderLayout);
            storiesAdapter.setOnLoadMoreListener(getLoadMoreListener());
            storiesAdapter.setNewData(mStories);
            mRecyclerView.scrollToPosition(0);
            setTitle("主页");
            return;
        }
        storiesAdapter.setOnLoadMoreListener(null);
        ZhiHuHttpHelper.getInstance().getTheme(mThemes.getOthers().get(themePosition).getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .retry(2)
                .subscribe(new Subscriber<Theme>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "获取主题内容列表失败", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onError_themeSelected: ", e);
                    }

                    @Override
                    public void onNext(Theme theme) {
                        switchTheme(theme);
                    }
                });

    }

    private void switchTheme(Theme theme) {
        if (mThemeHeader == null) {
            mThemeHeader = new ThemeHeader(this);
        }
        ThemeEditorView mEditorView = new ThemeEditorView(this, theme.getEditors());
        setTitle(theme.getName());
        mThemeHeader.setText(theme.getDescription());
        Glide.with(this).load(theme.getImage()).into(mThemeHeader.getImageView());
        storiesAdapter.removeAllHeaderView();
        storiesAdapter.addHeaderView(mThemeHeader.getView());
        storiesAdapter.addHeaderView(mEditorView);
        storiesAdapter.setNewData(theme.getStories());
        mRecyclerView.scrollToPosition(0);
    }

    public void setTitle(String title) {
        if (title.equals("首页") && newsThemeCurrent != -1) return;
        super.setTitle(title);
    }

    private void toggleNightMode() {
        if (isNightMode) {
            setTheme(R.style.AppTheme_DayTheme);
            getApplication().setTheme(R.style.AppTheme_DayTheme);
        } else {
            setTheme(R.style.AppTheme_NightTheme);
            getApplication().setTheme(R.style.AppTheme_NightTheme);
        }
        isNightMode = !isNightMode;
        modeSwitchAnimation();
        refreshUI();
    }

    private void modeSwitchAnimation() {
        final View decorView = getWindow().getDecorView();
        Bitmap cacheBitmap = getCacheBitmapFromView(decorView);
        if (decorView instanceof ViewGroup && cacheBitmap != null) {
            final ImageView view = new ImageView(this);
            //view.setBackgroundDrawable(new BitmapDrawable(getResources(), cacheBitmap));
            view.setImageBitmap(cacheBitmap);
            ViewGroup.LayoutParams layoutParam = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ((ViewGroup) decorView).addView(view, layoutParam);
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    ((ViewGroup) decorView).removeView(view);
                }
            });
            objectAnimator.start();
        }
    }

    private void refreshUI() {
        AttrsValueHolder attrs = new AttrsValueHolder(this);
        MyApplication.setAttrs(attrs);
        //
        mRecyclerView.setBackgroundColor(attrs.backGroundColor);
        //
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(attrs.colorPrimary));
        //
        if (isNightMode) navHeader.setBackgroundResource(R.drawable.side_nav_bar_night);
        else navHeader.setBackgroundResource(R.drawable.side_nav_bar_day);
        //
        navigationView.setBackgroundColor(attrs.cvBackGroundColor);
        //
        if (isNightMode)
            navigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_item_bg_night));
        else
            navigationView.setItemTextColor(getResources().getColorStateList(R.color.nav_item_bg_day));
        //statusBar
        BarUtils.setColor(this,attrs.colorPrimary);
        //To storiesAdapter
        EventBus.getDefault().post(new ToggleNightMode(isNightMode, attrs));
        storiesAdapter.notifyDataSetChanged();
    }

    private Bitmap getCacheBitmapFromView(View view) {
        final boolean drawingCacheEnabled = true;
        view.setDrawingCacheEnabled(drawingCacheEnabled);
        view.buildDrawingCache(drawingCacheEnabled);
        final Bitmap drawingCache = view.getDrawingCache();
        Bitmap bitmap;
        if (drawingCache != null) {
            bitmap = Bitmap.createBitmap(drawingCache);
            view.setDrawingCacheEnabled(false);
        } else {
            bitmap = null;
        }
        return bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(storiesAdapter);
    }

    private BaseQuickAdapter.RequestLoadMoreListener getLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNewsBefore();
            }
        };
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

    class RecyclerViewListener extends RecyclerView.OnScrollListener {
        private int lastPosition;
        private MainActivity activity;

        RecyclerViewListener(Activity activity) {
            this.activity = (MainActivity) activity;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            StoriesAdapter mAdapter = (StoriesAdapter) recyclerView.getAdapter();
            int position = layoutManager.findFirstVisibleItemPosition() - mAdapter.getHeaderLayoutCount();
            List<StoriesBean> data = mAdapter.getData();
            if (data.size() == 0) return;
            if (lastPosition != position) {
                if (position < 0) {
                    activity.setTitle("首页");
                } else if (data.get(position).getHeaderDate() != null) {
                    activity.setTitle(data.get(position).getHeaderDate());
                }
                if (position <= 3) {
                    mFab.setVisibility(View.GONE);
                } else {
                    mFab.setVisibility(View.VISIBLE);
                }
            }
            lastPosition = position;
        }
    }
}
