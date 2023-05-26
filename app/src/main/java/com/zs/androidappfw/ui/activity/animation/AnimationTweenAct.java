package com.zs.androidappfw.ui.activity.animation;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.ui.base.BaseActivity;

import java.util.List;

public class AnimationTweenAct extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_tween);

        // 补间动画合集效果
        /* 由旋转动画效果结论：
         * 在 xml 文件中给 alpha 和 scale 两种动画都设置repeatMode和repeatCount（虽然IDE工具没有提示这两种属性，根据API文档可知具有这两个属性）
         * */
        ImageView tweenIv = findViewById(R.id.animation_tween_iv);
        Animation tweenAnimation = AnimationUtils.loadAnimation(AnimationTweenAct.this, R.anim.animation_tween);
        tweenAnimation.setRepeatCount(Animation.INFINITE);
        tweenAnimation.setRepeatMode(Animation.RESTART);
        tweenIv.startAnimation(tweenAnimation);

        // 透明度效果
        ImageView alphaIv = findViewById(R.id.animation_tween_alpha_iv);
        Animation alphaAnimation = AnimationUtils.loadAnimation(AnimationTweenAct.this, R.anim.animation_tween_alpha);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.RESTART);
        alphaIv.startAnimation(alphaAnimation);

        // 缩放效果
        ImageView scaleIv = findViewById(R.id.animation_tween_scale_iv);
        Animation scaleAnimation = AnimationUtils.loadAnimation(AnimationTweenAct.this, R.anim.animation_tween_scale);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);
        scaleIv.startAnimation(scaleAnimation);

        // 旋转效果
        ImageView rotateIv = findViewById(R.id.animation_tween_rotate_iv);
        Animation rotateAnimation = AnimationUtils.loadAnimation(AnimationTweenAct.this, R.anim.animation_tween_rotate);
        /* 由此可知，某些属性设置到AnimationSet对象上没有效果，
         * 必须设置到 AlphaAnimation、ScaleAnimation、RotateAnimation、TranslateAnimation 四个具体的动效对象上才有效果
         * */
        if (rotateAnimation instanceof AnimationSet) {
            List<Animation> list = ((AnimationSet) rotateAnimation).getAnimations();
            list.get(0).setRepeatCount(Animation.INFINITE);
            list.get(0).setRepeatMode(Animation.REVERSE);
        }
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.REVERSE);
        rotateIv.startAnimation(rotateAnimation);

        // 位移效果
        ImageView translateIv = findViewById(R.id.animation_tween_translate_iv);
        Animation translateAnimation = AnimationUtils.loadAnimation(AnimationTweenAct.this, R.anim.animation_tween_translate);
        translateAnimation.setRepeatCount(Animation.INFINITE);
        translateAnimation.setRepeatMode(Animation.REVERSE);
        translateIv.startAnimation(translateAnimation);
    }
}
