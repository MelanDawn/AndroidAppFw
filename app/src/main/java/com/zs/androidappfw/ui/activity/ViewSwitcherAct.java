package com.zs.androidappfw.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;


// Created by zhangs on 2019/3/10.

public class ViewSwitcherAct extends BaseActivity {

    private ViewSwitcher switcher;
    private Factory factory;
    private int mIndex = 0;
    private int mMaxIndex = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_view_switcher);
        test();
    }

    public void test() {
        switcher = findViewById(R.id.view_switcher_demo_1);
        Button pre = findViewById(R.id.view_switcher_demo_1_pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                previous();
            }
        });
        Button next = findViewById(R.id.view_switcher_demo_1_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next();
            }
        });
        factory = new Factory(this);
        switcher.setFactory(factory);

        factory.update();
        switcher.showNext();
    }

    private void previous() {
        if (mIndex > 0) {
            mIndex--;
            factory.update();
            switcher.setInAnimation(this, android.R.anim.slide_in_left);
            switcher.setOutAnimation(this, android.R.anim.slide_out_right);
            switcher.showPrevious();
        }
    }

    private void next() {
        if (mIndex < mMaxIndex) {
            mIndex++;
            factory.update();
            switcher.setInAnimation(this, android.R.anim.slide_in_left);
            switcher.setOutAnimation(this, android.R.anim.slide_out_right);
            switcher.showNext();
        }
    }

//    class ViewSwitcherItemData {
//        int icon;
//        String name;
//        String checkedName;
//
//        ViewSwitcherItemData(int icon, String name, String checkedName) {
//            this.icon = icon;
//            this.name = name;
//            this.checkedName = checkedName;
//        }
//    }

    class Factory implements ViewSwitcher.ViewFactory {

        int[] mImages = {R.drawable.act_iv_demo_h, R.drawable.act_iv_demo_v, R.drawable.act_iv_demo_mini};
        String[] mTexts = {"北京", "上海", "广州"};
        String[] mCheckedTexts = {"深圳", "杭州", "苏州"};
        int index = 0;
//        int maxIndex = 2;

        private Context context;
//        ImageView imageView;
//        TextView textView;
//        CheckedTextView checkedTextView;

        Factory(Context context) {
            this.context = context;
        }

        @Override
        public View makeView() {
            View view = LayoutInflater.from(context).inflate(R.layout.act_view_switcher_item, null);
            ImageView imageView = view.findViewById(R.id.view_switcher_item_iv);
            imageView.setImageResource(mImages[index]);
            TextView textView = view.findViewById(R.id.view_switcher_item_tv);
            textView.setText(mTexts[index]);
            CheckedTextView checkedTextView = view.findViewById(R.id.view_switcher_item_ctv);
            checkedTextView.setText(mCheckedTexts[index]);
            index++;
            return view;
        }

        void update() {
//            imageView.setImageResource(mImages[mIndex]);
//            textView.setText(mTexts[mIndex]);
//            checkedTextView.setText(mCheckedTexts[mIndex]);
        }
    }
}
