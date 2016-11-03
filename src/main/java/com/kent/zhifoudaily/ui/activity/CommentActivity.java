package com.kent.zhifoudaily.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.adapter.CommentAdapter;
import com.kent.zhifoudaily.entity.Comment;
import com.kent.zhifoudaily.retrofit.ZhiHuHttpHelper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setSupportActionBar((Toolbar) findViewById(R.id.comment_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        initView();
        initDatas();
    }

    public static void Launch(Activity from, int newsId) {
        Intent i = new Intent(from, CommentActivity.class);
        i.putExtra("newsId", newsId);
        from.startActivity(i);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recyclerView);
        setTitle("评论");
    }

    private void initDatas() {
        getCommentById(getIntent().getIntExtra("newsId", -1));
        mAdapter = new CommentAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(0xffcdcdcd).size(2).build());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void getCommentById(int id) {
        if (id == -1) {
            Toast.makeText(this, "新闻评论已过期...", Toast.LENGTH_SHORT).show();
            return;
        }
        Observable<Comment> longComment = ZhiHuHttpHelper.getInstance().getLongComment(id);
        Observable<Comment> shortComment = ZhiHuHttpHelper.getInstance().getShortComment(id);
        Observable
                .zip(longComment, shortComment, new Func2<Comment, Comment, List<Comment.CommentsBean>>() {
                    @Override
                    public List<Comment.CommentsBean> call(Comment comment, Comment comment2) {
                        List<Comment.CommentsBean> comments = comment.getComments();
                        comments.addAll(comment2.getComments());
                        Collections.sort(comments, new Comparator<Comment.CommentsBean>() {
                            @Override
                            public int compare(Comment.CommentsBean o1, Comment.CommentsBean o2) {
                                if (o1.getTime() > o2.getTime()) return 1;
                                return 0;
                            }
                        });
                        return comments;
                    }
                })
                .retry(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Comment.CommentsBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(CommentActivity.this, "获取评论失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<Comment.CommentsBean> commentsBeen) {
                        updateComment(commentsBeen);
                    }
                });
    }

    private void updateComment(List<Comment.CommentsBean> comments) {
        if (comments == null) return;
        if (comments.size()==0){
            findViewById(R.id.comment_noComment).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.comment_noComment).setVisibility(View.GONE);
        }
        setTitle(comments.size() + "条点论");
        mAdapter.addData(comments);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
