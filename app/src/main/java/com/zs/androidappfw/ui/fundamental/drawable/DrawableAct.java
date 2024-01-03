package com.zs.androidappfw.ui.fundamental.drawable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class DrawableAct extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawable);
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_drawable;
    }

    public void toDrawableBitmap(View v) {
        startActivity(BitmapDrawableAct.class);
    }

    public void toNinePatchDrawable(View v) {
        startActivity(NinePatchDrawableAct.class);
    }

    public void toLayerDrawable(View v) {
        startActivity(LayerDrawableAct.class);
    }

    public void toStateListDrawable(View v) {
        startActivity(StateListDrawableAct.class);
    }

    public void toLevelDrawable(View v) {
        startActivity(LevelListDrawableAct.class);
    }

    public void toTransitionDrawable(View v) {
        startActivity(TransitionDrawableAct.class);
    }

    public void toInsertDrawable(View v) {
        startActivity(InsetDrawableAct.class);
    }

    public void toClipDrawable(View v) {
        startActivity(ClipDrawableAct.class);
    }

    public void toScaleDrawable(View v) {
        startActivity(ScaleDrawableAct.class);
    }
    public void toShapeDrawable(View v) {
        startActivity(ShapeDrawableAct.class);
    }

    private void startActivity(Class<? extends BaseTitleActivity> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
