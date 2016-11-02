package com.kent.zhifoudaily.retrofit;

import com.kent.zhifoudaily.entity.NewsBefore;
import com.kent.zhifoudaily.entity.NewsLatest;
import com.kent.zhifoudaily.entity.WelcomeImage;


import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Kent ↗↗↗ on 2016/10/31.
 */

public interface ZhiHuInterface {

    @GET("start-image/{resolution}")
    Observable<WelcomeImage> getWelcomeImage(@Path("resolution") String solution);

    @GET("news/latest")
    Observable<NewsLatest> getNewsLatest();

    @GET("news/before/{date}")
    Observable<NewsBefore> getNewsBefore(@Path("date") String date);

}
