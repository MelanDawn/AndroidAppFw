package com.zs.androidappfw.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.zs.androidappfw.R;
import com.zs.androidappfw.utils.LUtil;

/* 从左到右、从上到下 两列元素排列，内部元素全部是正方形
* 以宽度为基准控制组件大小
*
* onMeasure
* onLayout
* onDraw
* 先测量大小，根据测量大小确定布局，最后绘制*/

public class TwoColumnLayout extends ViewGroup {

    private static final String TAG = "TwoColumnLayout";

    private static final int TWO_COLUMN_DISTANCE = 10;
    private static final int TWO_ROW_DISTANCE = 10;

    private int mTwoColumnDistance = -1;
    private int mTwoRowDistance = -1;

//    public int getTwoColumnDistance() {
//        return mTwoColumnDistance;
//    }

    public void setTwoColumnDistance(int twoColumnDistance) {
        this.mTwoColumnDistance = twoColumnDistance;
    }

//    public int getTwoRowDistance() {
//        return mTwoRowDistance;
//    }

    public void setTwoRowDistance(int twoRowDistance) {
        this.mTwoRowDistance = twoRowDistance;
    }

    public TwoColumnLayout(Context context) {
        super(context);
        LUtil.d(TAG, "TwoColumnLayout 1 param");
    }

    public TwoColumnLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        LUtil.d(TAG, "TwoColumnLayout 2 params");
    }

    public TwoColumnLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        LUtil.d(TAG, "TwoColumnLayout 3 params");
    }

    public TwoColumnLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);

        LUtil.d(TAG, "TwoColumnLayout 4 params");

        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.TwoColumnLayout, defStyleAttr, defStyleRes);

        int twoColumnDistance;
        int twoRowDistance;

        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.TwoColumnLayout_twoColumnDistance:
                    twoColumnDistance = typedArray.getDimensionPixelSize(attr, -1);
                    if (twoColumnDistance >= 0) setTwoColumnDistance(twoColumnDistance);
                    break;
                case R.styleable.TwoColumnLayout_twoRowDistance:
                    twoRowDistance = typedArray.getDimensionPixelSize(attr, -1);
                    if (twoRowDistance >= 0) setTwoRowDistance(twoRowDistance);
                    break;
            }
        }
        typedArray.recycle();

        if (mTwoColumnDistance < 0) setTwoColumnDistance(TWO_COLUMN_DISTANCE);
        if (mTwoRowDistance < 0) setTwoRowDistance(TWO_ROW_DISTANCE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LUtil.d(TAG, "onMeasure");

//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height;

        // 正方形子组件边长，中间间隔由其他控制，padding仅仅控制左上角或右上角
        int squareSideLength = (width - mTwoColumnDistance - getPaddingStart() - getPaddingEnd()) / 2;

        // 内部元素组成的行数
        int row = (getChildCount() + 1) >> 1;
        // 所有子组件的水平总间隔
        int rowDistance = (row - 1) >= 0 ? (mTwoRowDistance * (row -1)) : 0;
        height = squareSideLength * row + rowDistance + getPaddingTop() + getPaddingBottom();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        int childrenSideLengthMeasureSpec = MeasureSpec.makeMeasureSpec(squareSideLength, MeasureSpec.AT_MOST);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // measureChildren()、measureChild() 方法与padding有关系
            childView.measure(childrenSideLengthMeasureSpec, childrenSideLengthMeasureSpec);
            measureChildWithMargins(childView, childrenSideLengthMeasureSpec, 0, childrenSideLengthMeasureSpec, 0);
        }

        LUtil.d(TAG, "width=" + width + " columnDistance=" + mTwoColumnDistance + " sideLength=" + squareSideLength);
        LUtil.d(TAG, " padding start=" + getPaddingStart() + " end=" + getPaddingEnd());
        LUtil.d(TAG, " padding left=" + getPaddingLeft() + " right=" + getPaddingRight());

        // 测量完成后要设置对应的测量值
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LUtil.d(TAG, "onLayout");
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childMeasuredWidth = childView.getMeasuredWidth();
            int childMeasuredHeight = childView.getMeasuredHeight();
            LUtil.d(TAG, childView.getLayoutParams().toString());
            MarginLayoutParams margins = (MarginLayoutParams) childView.getLayoutParams();
            LUtil.d(TAG, "child width=" + childMeasuredWidth + " height=" + childMeasuredHeight);
            LUtil.d(TAG, "child margin " + margins.leftMargin + " " + margins.topMargin + " " + margins.rightMargin + " " + margins.bottomMargin);
            int line = i / 2;
            if ((i & 1) == 0) {
                childView.layout(getPaddingLeft() + margins.leftMargin,
                        getPaddingTop() + margins.topMargin + (margins.topMargin + margins.bottomMargin + childView.getMeasuredHeight() + mTwoRowDistance) * line,
                        getPaddingLeft() + margins.leftMargin + childView.getMeasuredWidth(),
                        getPaddingTop() + margins.topMargin + (margins.topMargin + margins.bottomMargin + childView.getMeasuredHeight() + mTwoRowDistance) * line + childView.getMeasuredHeight());
            } else {
                // 从右边向左边计算
                childView.layout(getMeasuredWidth() - getPaddingRight() - margins.leftMargin - margins.rightMargin - childView.getMeasuredWidth(),
                        getPaddingTop() + margins.topMargin + (margins.topMargin + margins.bottomMargin + childView.getMeasuredHeight() + mTwoRowDistance) * line,
                        getMeasuredWidth() - getPaddingRight() - margins.rightMargin,
                        getPaddingTop() + margins.topMargin + (margins.topMargin + margins.bottomMargin + childView.getMeasuredHeight() + mTwoRowDistance) * line + childView.getMeasuredHeight());
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LUtil.d(TAG, "onDraw");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 当前视图填充完毕（并且所有子视图已经填充完毕）
        LUtil.d(TAG, "onFinishInflate");
    }

    @Override
    public void setLayoutParams(LayoutParams params) {
        super.setLayoutParams(params);
        LUtil.d(TAG, "setLayoutParams");
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LUtil.d(TAG, "generateLayoutParams attrs");
        int len = attrs.getAttributeCount();
        for (int i = 0; i < len; i++) {
            LUtil.d(TAG, attrs.getAttributeName(i) + "-->" + attrs.getAttributeNameResource(i));
        }
        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        LUtil.d(TAG, "generateLayoutParams lp");
        return new MyLayoutParams(lp);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    static class MyLayoutParams extends MarginLayoutParams {

        private static final String TAG = MyLayoutParams.class.getSimpleName();

        MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        MyLayoutParams(int width, int height) {
            super(width, height);
        }

        MyLayoutParams(LayoutParams lp) {
            super(lp);
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            super.setBaseAttributes(a, widthAttr, heightAttr);
            LUtil.d(TAG, "setBaseAttributes");
        }

        @Override
        public boolean isMarginRelative() {
            LUtil.d(TAG, "isMarginRelative");
            return super.isMarginRelative();
        }

        @Override
        public int getLayoutDirection() {
            LUtil.d(TAG, "MyLayoutParams getLayoutDirection");
            return super.getLayoutDirection();
        }

        @Override
        public void setLayoutDirection(int layoutDirection) {
            super.setLayoutDirection(layoutDirection);
            LUtil.d(TAG, "setLayoutDirection");
        }

        @Override
        public void resolveLayoutDirection(int layoutDirection) {
            super.resolveLayoutDirection(layoutDirection);
            LUtil.d(TAG, "MyLayoutParams resolveLayoutDirection " + layoutDirection);
        }

        @Override
        public int getMarginEnd() {
            LUtil.d(TAG, "getMarginEnd");
            return super.getMarginEnd();
        }

        @Override
        public int getMarginStart() {
            LUtil.d(TAG, "getMarginStart");
            return super.getMarginStart();
        }

        @Override
        public void setMarginEnd(int end) {
            super.setMarginEnd(end);
            LUtil.d(TAG, "setMarginEnd");
        }

        @Override
        public void setMarginStart(int start) {
            super.setMarginStart(start);
            LUtil.d(TAG, "setMarginStart");
        }

        @Override
        public void setMargins(int left, int top, int right, int bottom) {
            super.setMargins(left, top, right, bottom);
            LUtil.d(TAG, "setMargins");
        }
    }
}
