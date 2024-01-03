package com.zs.androidappfw.ui.fundamental.font;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class FontActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_font);

        TextView textView1 = findViewById(R.id.font_times_1);
        TextView textView2 = findViewById(R.id.font_times_2);
        TextView textView3 = findViewById(R.id.font_times_3);
        TextView textView4 = findViewById(R.id.font_times_4);

        Typeface typeface1 = Typeface.create("font/times.ttf", Typeface.NORMAL);
        textView1.setTypeface(typeface1);

        Typeface typeface2 = Typeface.create("font/timesbd.ttf", Typeface.BOLD);
        textView2.setTypeface(typeface2);

        Typeface typeface3 = Typeface.createFromAsset(this.getAssets(), "fonts/timesbi.ttf");
        textView3.setTypeface(typeface3);

        Typeface typeface4 = Typeface.createFromAsset(this.getAssets(), "fonts/timesi.ttf");
        textView4.setTypeface(typeface4);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_font;
    }
}
