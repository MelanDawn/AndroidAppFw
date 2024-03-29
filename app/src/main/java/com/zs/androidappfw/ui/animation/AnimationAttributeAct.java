package com.zs.androidappfw.ui.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.base.BaseTitleActivity;

public class AnimationAttributeAct extends BaseTitleActivity {

    AnimatorSet attributeSet;
    ObjectAnimator alphaAnimation;
    AnimatorSet scaleSet;
    ObjectAnimator rotateAnimation;
    AnimatorSet translateSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_animation_attribute);

        // 透明度效果
        ImageView alphaIv = findViewById(R.id.animation_attribute_alpha_iv);
        alphaAnimation = ObjectAnimator.ofFloat(alphaIv, "alpha", 0F, 0.5F, 0.8F, 1F);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(ValueAnimator.REVERSE);
        alphaAnimation.start();

        // 缩放效果
        ImageView scaleIv = findViewById(R.id.animation_attribute_scale_iv);
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(scaleIv, "scaleX", 0F, 2F);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(scaleIv, "scaleY", 0F, 2F);
        scaleXAnimation.setDuration(2000);
        scaleYAnimation.setDuration(2000);
        scaleXAnimation.setRepeatCount(Animation.INFINITE);
        scaleYAnimation.setRepeatCount(Animation.INFINITE);
        scaleXAnimation.setRepeatMode(ValueAnimator.REVERSE);
        scaleYAnimation.setRepeatMode(ValueAnimator.REVERSE);
        scaleSet = new AnimatorSet();
        scaleSet.playTogether(scaleXAnimation, scaleYAnimation);
        scaleSet.start();

        // 旋转效果
        ImageView rotateIv = findViewById(R.id.animation_attribute_rotate_iv);
        rotateAnimation = ObjectAnimator.ofFloat(rotateIv, "rotation", 0F, 270F);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnimation.start();

        // 位移效果
        ImageView translateIv = findViewById(R.id.animation_attribute_translate_iv);
        ObjectAnimator translateXAnimation = ObjectAnimator.ofFloat(translateIv, "translationX", 20F, 100F);
        ObjectAnimator translateYAnimation = ObjectAnimator.ofFloat(translateIv, "translationY", 20F, 100F);
        translateXAnimation.setDuration(2000);
        translateYAnimation.setDuration(2000);
        translateXAnimation.setRepeatCount(Animation.INFINITE);
        translateYAnimation.setRepeatCount(Animation.INFINITE);
        translateXAnimation.setRepeatMode(ValueAnimator.REVERSE);
        translateYAnimation.setRepeatMode(ValueAnimator.REVERSE);
        translateSet = new AnimatorSet();
        translateSet.playTogether(translateXAnimation, translateYAnimation);
        translateSet.start();

        ImageView attributeIv = findViewById(R.id.animation_attribute_iv);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(attributeIv, "alpha", 1.0f, 0.5f, 0.8f, 1.0f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(attributeIv, "scaleX", 0.5f, 1.0f);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(attributeIv, "scaleY", 0.5f, 2.0f);
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(attributeIv, "rotation", 0, 360);
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(attributeIv, "translationX", 100, 200);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(attributeIv, "translationY", 100, -300);
        alphaAnim.setRepeatCount(Animation.INFINITE);
        scaleXAnim.setRepeatCount(Animation.INFINITE);
        scaleYAnim.setRepeatCount(Animation.INFINITE);
        rotateAnim.setRepeatCount(Animation.INFINITE);
        transXAnim.setRepeatCount(Animation.INFINITE);
        transYAnim.setRepeatCount(Animation.INFINITE);
        alphaAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleXAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleYAnim.setRepeatMode(ValueAnimator.REVERSE);
        rotateAnim.setRepeatMode(ValueAnimator.REVERSE);
        transXAnim.setRepeatMode(ValueAnimator.REVERSE);
        transYAnim.setRepeatMode(ValueAnimator.REVERSE);
        attributeSet = new AnimatorSet();
        attributeSet.setDuration(3000);
        attributeSet.playTogether(alphaAnim, scaleXAnim, scaleYAnim, rotateAnim, transXAnim, transYAnim);
        attributeSet.start();
    }

    @Override
    protected int getTitleResId() {
        return R.string.title_animation_attribute;
    }

    @Override
    protected void onStop() {
        super.onStop();
        attributeSet.end();
        alphaAnimation.end();
        scaleSet.end();
        rotateAnimation.end();
        translateSet.end();
    }
}
