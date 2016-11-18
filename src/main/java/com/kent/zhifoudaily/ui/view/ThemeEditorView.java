package com.kent.zhifoudaily.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kent.zhifoudaily.R;
import com.kent.zhifoudaily.adapter.GlideCircleTransform;
import com.kent.zhifoudaily.entity.Theme;
import com.kent.zhifoudaily.utils.ConvertUtils;

import java.util.List;

public class ThemeEditorView extends LinearLayout {
    private Context mContext;
    private List<Theme.EditorsBean> editors;

    public ThemeEditorView(Context context, List<Theme.EditorsBean> editors) {
        super(context);
        mContext = context;
        this.editors = editors;
        init();
    }

    private void init() {
        int px = ConvertUtils.dp2px(mContext, 8);
        setPadding(px, px, px, px);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        TextView textView = new TextView(mContext);
        textView.setText(getResources().getString(R.string.editor));
        addView(textView);
        for (Theme.EditorsBean editor : editors) {
            ImageView avatar = new ImageView(mContext);
            addView(avatar);
            LinearLayout.LayoutParams lp = (LayoutParams) avatar.getLayoutParams();
            lp.leftMargin=px;
            lp.width = px*4;
            lp.height = px*4;
            Glide.with(mContext).load(editor.getAvatar()).transform(new GlideCircleTransform(mContext)).into(avatar);
        }
    }
}
