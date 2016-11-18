package com.kent.zhifoudaily.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.entity.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<Comment.CommentsBean, BaseViewHolder> {
    public CommentAdapter(int layoutResId, List<Comment.CommentsBean> data) {
        super(layoutResId, data);
    }

    public CommentAdapter(Context context) {
        this(R.layout.layout_comment, new ArrayList<Comment.CommentsBean>());
        mContext = context;
    }

    private Context mContext;

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Comment.CommentsBean comments) {
        baseViewHolder
                .setText(R.id.comment_userName, comments.getAuthor())
                .setText(R.id.comment_content, comments.getContent())
                .setText(R.id.comment_likes, " " + comments.getLikes())
                .setText(R.id.comment_time, getConcertTime(comments.getTime() * 1000));
        ImageView icon = baseViewHolder.getView(R.id.comment_avatar);
        Glide.with(mContext).load(comments.getAvatar()).transform(new GlideCircleTransform(mContext)).into(icon);
        Comment.CommentsBean.ReplyToBean replyTo = comments.getReply_to();
        TextView to = baseViewHolder.getView(R.id.comment_to);
        if (replyTo != null) {
            String toStr = "<b>//" + replyTo.getAuthor() + "</b>：" + replyTo.getContent();
            if (replyTo.getStatus() == 3) {
                toStr = replyTo.getError_msg();
            }
            to.setBackgroundColor(0xfff0f0f0);
            to.setText(Html.fromHtml(toStr));
            to.setVisibility(View.VISIBLE);
        } else {
            to.setVisibility(View.GONE);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getConcertTime(long timeStamp) {
        Date date = new Date(timeStamp);
        return new SimpleDateFormat("MM-dd HH:mm").format(date);
    }

}
