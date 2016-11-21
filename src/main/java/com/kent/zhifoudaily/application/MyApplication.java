package com.kent.zhifoudaily.application;

import android.app.Application;

import com.kent.zhifoudaily.entity.AttrsValueHolder;

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static AttrsValueHolder attrs;

    public static AttrsValueHolder getAttrs() {
        return attrs;
    }

    public static void setAttrs(AttrsValueHolder attrs) {
        MyApplication.attrs = attrs;
    }
}
