package com.kent.zhifoudaily.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.entity.StartImage;
import com.kent.zhifoudaily.retrofit.ZhiHuHttpHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class EntryActivity extends Activity {

    private static final String TAG = "EntryActivity";

        private static final int animationTime = 2000;
//    private static final int animationTime = 200;

    private static final float scaleMax = 1.13F;

//    private static final int delayTime = 100;
    private static final int delayTime = 1000;

    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getLauncherImage();

        mImageView = (ImageView) findViewById(R.id.iv_splash);
        mTextView = (TextView) findViewById(R.id.tv_splash);

    }

    public void getLauncherImage() {
        ZhiHuHttpHelper.getInstance().getWelcomeImage("1920*1080")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        mHandler.sendEmptyMessageDelayed(0, delayTime);
                        mHandler.sendEmptyMessageDelayed(1, delayTime + animationTime);
                    }
                })
                .subscribe(new Subscriber<StartImage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w(TAG, "onError: ", e);
                        Glide.with(EntryActivity.this).load(R.drawable.welcome).into(mImageView);
                        mTextView.setText(R.string.zhiFou);
                    }

                    @Override
                    public void onNext(StartImage welcomeImage) {
                        if (welcomeImage != null) {
                            mTextView.setText(welcomeImage.getText());
                            Glide.with(EntryActivity.this).load(welcomeImage.getImg())
                                    .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.welcome).into(mImageView);
                        }
                    }
                });
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                startImageAnimation();
            } else if (msg.what == 1) {
                startActivity(new Intent(EntryActivity.this, MainActivity.class));
                EntryActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
            return false;
        }
    });

    private void startImageAnimation() {
        PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 1f, scaleMax);
        PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 1f, scaleMax);
        ObjectAnimator.ofPropertyValuesHolder(mImageView, holderX, holderY).setDuration(animationTime).start();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
