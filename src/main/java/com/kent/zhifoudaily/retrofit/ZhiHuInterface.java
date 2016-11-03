package com.kent.zhifoudaily.retrofit;

import com.kent.zhifoudaily.entity.Comment;
import com.kent.zhifoudaily.entity.News;
import com.kent.zhifoudaily.entity.NewsBefore;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.StartImage;
import com.kent.zhifoudaily.entity.StoryExtra;


import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Kent ↗↗↗ on 2016/10/31.
 */

public interface ZhiHuInterface {

    @GET("start-image/{resolution}")
    Observable<StartImage> getWelcomeImage(@Path("resolution") String solution);

    @GET("news/latest")
    Observable<NewsLatest> getNewsLatest();

    @GET("news/before/{date}")
    Observable<NewsBefore> getNewsBefore(@Path("date") String date);

    @GET("news/{id}")
    Observable<News> getNews(@Path("id") int id);

    @GET("story-extra/{id}")
    Observable<StoryExtra> getStoryExtra(@Path("id") int id);

    @GET("story/{id}/long-comments")
    Observable<Comment> getLongComment(@Path("id") int id);

    @GET("story/{id}/short-comments")
    Observable<Comment> getShortComment(@Path("id") int id);

}
