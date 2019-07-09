package com.zs.androidappfw.ui.activity.fundamental.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

public class BitmapDrawableAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable_bitmap);

        Resources res = getResources();
        Drawable png = res.getDrawable(R.drawable.act_iv_demo_h);
        Drawable jpg = res.getDrawable(R.drawable.drawable_bitmap_jpg);
        Drawable gif = res.getDrawable(R.drawable.drawable_bitmap_gif);

        ImageView pngIv = findViewById(R.id.drawable_png);
        pngIv.setImageDrawable(png);
        ImageView jpgIv = findViewById(R.id.drawable_jpg);
        jpgIv.setImageDrawable(jpg);
        ImageView gifIv = findViewById(R.id.drawable_gif);
        gifIv.setImageDrawable(gif);
    }
}
