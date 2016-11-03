package com.kent.zhifoudaily.retrofit;

import android.annotation.SuppressLint;

import com.kent.zhifoudaily.entity.Comment;
import com.kent.zhifoudaily.entity.News;
import com.kent.zhifoudaily.entity.NewsBefore;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StartImage;
import com.kent.zhifoudaily.entity.StoryExtra;


import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

/**
 * Created by Kent ↗↗↗ on 2016/10/31.
 */

public class ZhiHuHttpHelper {
    private static final String BaseUrl = "http://news-at.zhihu.com/api/4/";

    private static final class ZhiHuHttpHolder {
        private static final ZhiHuHttpHelper INSTANCE = new ZhiHuHttpHelper();
    }

    private Retrofit retrofit;

    private ZhiHuInterface zhiHuApi;

    private ZhiHuHttpHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        zhiHuApi = retrofit.create(ZhiHuInterface.class);
    }

    public static ZhiHuHttpHelper getInstance() {
        return ZhiHuHttpHolder.INSTANCE;
    }

    public Observable<StartImage> getWelcomeImage(String resolution) {
        return zhiHuApi.getWelcomeImage(resolution);
    }

    public Observable<NewsLatest> getNewsLatest() {
        return zhiHuApi.getNewsLatest();
    }

    @SuppressLint("SimpleDateFormat")
    public Observable<NewsBefore> getNewsBefore(int dayBefore) {
        Date date = new Date(new Date().getTime() - dayBefore * 24 * 60 * 60 * 1000);
        return zhiHuApi.getNewsBefore(new SimpleDateFormat("yyyyMMdd").format(date));
    }

    public Observable<News> getNews(int id) {
        return zhiHuApi.getNews(id);
    }

    public Observable<StoryExtra> getStoryExtra(int id) {
        return zhiHuApi.getStoryExtra(id);
    }

    public Observable<Comment> getLongComment(int id) {
        return zhiHuApi.getLongComment(id);
    }

    public Observable<Comment> getShortComment(int id) {
        return zhiHuApi.getShortComment(id);
    }
}
