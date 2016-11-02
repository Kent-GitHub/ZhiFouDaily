package com.kent.zhifoudaily.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ShadowImageView extends ImageView {
    public ShadowImageView(Context context) {
        this(context, null);
    }

    public ShadowImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        init();
    }

    private int mHeight, mWidth;
    private Paint mPaint;

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int targetColor=0x80000000;
        @SuppressLint("DrawAllocation")
        LinearGradient mShader = new LinearGradient(0, getHeight()/2,
                0, getHeight(), Color.TRANSPARENT, targetColor, Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        canvas.drawRect(0, getHeight()/2, getWidth(), getHeight(), mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
