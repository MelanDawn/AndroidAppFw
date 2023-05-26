package com.zs.androidappfw.ui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zs.androidappfw.R;
import com.zs.androidappfw.utils.LUtil;

public class MusicSwitchView extends View {
    private static final String TAG = "MusicSwitchView";

    // view state
    public static final int STATE_STARTED = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_FINISHED = 3;

    private static final int PROGRESS_WIDTH = 2;
    private static final int SWITCH_SIDE_LENGTH = 20;

    private int mState = STATE_PAUSED;

    private boolean mEnabled = false;
    private int mProgressWidth = -1;
    private int mSwitchWidth = -1;
    private int mSwitchHeight = -1;
    private int mSwitchSideLength = -1;
    private int mProgressColor = Color.GRAY;
    private int mProgressDefaultColor = Color.GREEN;

    // 绘制起始的角度
    private static final int STATED_ANGLE = -90;
    // 绘制扫过的角度
    private int mSweepAngle = 0;
    // 内圆半径
    private float mInsideRadius;
    // 外圆半径
    private float mOutSideRadius;
    // 开启状态的图片
    private Drawable mSwitchStared = null;
    // 关闭状态的图片
    private Drawable mSwitchPaused = null;

    private Paint mPaint = new Paint();

    private RectF mRectF = new RectF();

    public void setProgress(int value) {
        if (mState == STATE_STARTED) {
            if (value >= 100) mState = STATE_FINISHED;
            mSweepAngle = value * 360 / 100;
            postInvalidate();
        }
    }

    public int getState() {
        return mState;
    }

    public void setState(int state) {
        if (mState != state) {
            this.mState = state;
            invalidate();
        }
    }

    public boolean start() {
        if (mState == STATE_PAUSED) {
            setState(STATE_STARTED);
            return true;
        }
        return false;
    }

    public void pause() {
        if (mState == STATE_STARTED) {
            setState(STATE_PAUSED);
        }
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.mEnabled = enabled;
    }

    public int getProgressWidth() {
        return mProgressWidth;
    }

    public void setProgressWidth(int progressWidth) {
        this.mProgressWidth = progressWidth;
    }

    public int getSwitchWidth() {
        return mSwitchWidth;
    }

    public void setSwitchWidth(int switchWidth) {
        this.mSwitchWidth = switchWidth;
    }

    public int getSwitchHeight() {
        return mSwitchHeight;
    }

    public void setSwitchHeight(int switchHeight) {
        this.mSwitchHeight = switchHeight;
    }

    public int getProgressColor() {
        return mProgressColor;
    }

    public void setProgressColor(int progressColor) {
        this.mProgressColor = progressColor;
    }

    public int getProgressDefaultColor() {
        return mProgressDefaultColor;
    }

    public void setProgressDefaultColor(int progressDefaultColor) {
        this.mProgressDefaultColor = progressDefaultColor;
    }

    /*
    * 通过源码可知，1个参数与 2、3、4个参数不是一个体系，因此这里也分成两个体系。
    * 通过 1个参数 的构造函数创建的View无法通过findViewById找到；
    * 通过 2、3、4个参数的构造函数必须super父类两个参数及以上参数的构造函数，否则findViewById也会找不到
    * */
    public MusicSwitchView(Context context) {
        super(context);
        LUtil.d(TAG, "MusicSwitchView 1 param");
    }

    public MusicSwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        LUtil.d(TAG, "MusicSwitchView 2 params");
    }

    public MusicSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
        LUtil.d(TAG, "MusicSwitchView 3 params");
    }

    public MusicSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /* 由于 View 的四参数构造函数只能在API 21之后使用，因此这里不调用super四参数构造函数，自己实现*/
        super(context, attrs, defStyleAttr);

        LUtil.d(TAG, "MusicSwitchView 4 params");

        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.MusicSwitchView, defStyleAttr, defStyleRes);

        boolean enabled;
        int progressWidth;
        int switchWidth;
        int switchHeight;
        int progressColor;
        int progressDefaultColor;
//        Drawable background = null;

        boolean typeBoolean = false;
        int typeInteger = -1;
        float typeFloat = -1F;
        String typeString = "";
        int typeColor = -1;
        int typeDimension = -1;
        float typeFraction = -1F;
        int typeEnum = -1;
        int typeFlag = -1;
        // 注意：该值的类型根据值的具体类型而定，这里是String，上面有个Drawable类型
        String typeReference = "";

        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.MusicSwitchView_enabled:
                    enabled = typedArray.getBoolean(attr, false);
                    setEnabled(enabled);
                    break;
                case R.styleable.MusicSwitchView_progress_width:
                    progressWidth = typedArray.getDimensionPixelSize(attr, -1);
                    if (progressWidth > 0) setProgressWidth(progressWidth);
                    break;
                case R.styleable.MusicSwitchView_switch_width:
                    switchWidth = typedArray.getDimensionPixelSize(attr, -1);
                    if (switchWidth > 0) setSwitchWidth(switchWidth);
                    break;
                case R.styleable.MusicSwitchView_switch_height:
                    switchHeight = typedArray.getDimensionPixelSize(attr, -1);
                    if (switchHeight > 0) setSwitchHeight(switchHeight);
                    break;
                case R.styleable.MusicSwitchView_progress_color:
                    progressColor = typedArray.getColor(attr, Color.GREEN);
                    setProgressColor(progressColor);
                    break;
                case R.styleable.MusicSwitchView_progress_default_color:
                    progressDefaultColor = typedArray.getColor(attr, Color.GRAY);
                    setProgressDefaultColor(progressDefaultColor);
                    break;
//                case R.styleable.MusicSwitchView_background:
//                    background = typedArray.getDrawable(attr);
//                    break;

                case R.styleable.MusicSwitchView_type_boolean:
                    typeBoolean = typedArray.getBoolean(attr, false);
                    break;
                case R.styleable.MusicSwitchView_type_integer:
                    typeInteger = typedArray.getInt(attr, -1);
                    break;
                case R.styleable.MusicSwitchView_type_float:
                    typeFloat = typedArray.getFloat(attr, -1);
                    break;
                case R.styleable.MusicSwitchView_type_string:
                    typeString = typedArray.getString(attr);
                    break;
                case R.styleable.MusicSwitchView_type_color:
                    typeColor = typedArray.getInt(attr, Color.GRAY);
                    break;
                case R.styleable.MusicSwitchView_type_dimension:
                    typeDimension = typedArray.getDimensionPixelSize(attr, -1);
                    break;
                case R.styleable.MusicSwitchView_type_fraction:
                    typeFraction = typedArray.getFraction(attr, 1, 1, -1F);
                    break;
                case R.styleable.MusicSwitchView_type_enum:
                    typeEnum = typedArray.getIndex(attr);
                    break;
                case R.styleable.MusicSwitchView_type_flags:
                    typeFlag = typedArray.getInt(attr, -1);
                    break;
                case R.styleable.MusicSwitchView_type_reference:
                    typeReference = typedArray.getString(attr);
                    break;
            }
        }

        LUtil.d(TAG, "typeBoolean=" + typeBoolean);
        LUtil.d(TAG, "typeInteger=" + typeInteger);
        LUtil.d(TAG, "typeFloat=" + typeFloat);
        LUtil.d(TAG, "typeString=" + typeString);
        LUtil.d(TAG, "typeColor=" + typeColor);
        LUtil.d(TAG, "typeDimension=" + typeDimension);
        LUtil.d(TAG, "typeFraction=" + typeFraction);
        LUtil.d(TAG, "typeEnum=" + typeEnum);
        LUtil.d(TAG, "typeFlag=" + typeFlag);
        LUtil.d(TAG, "typeReference=" + typeReference);

//        if (background != null) {
//            setBackground(background);
//        }

        typedArray.recycle();

        mSwitchStared = Resources.getSystem().getDrawable(R.drawable.music_view_doing, null);
        mSwitchPaused = Resources.getSystem().getDrawable(R.drawable.music_view_paused, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LUtil.d(TAG, "onMeasure");

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (MeasureSpec.EXACTLY == widthMode || MeasureSpec.EXACTLY == heightMode) {
            int paddingHorizontal = getPaddingLeft() + getPaddingRight();
            int paddingVertical = getPaddingTop() + getPaddingBottom();
            if (MeasureSpec.EXACTLY == widthMode && MeasureSpec.EXACTLY == heightMode) {
                mOutSideRadius = Math.min(width, height);
                if (paddingHorizontal > paddingVertical) {
                    mOutSideRadius -= paddingHorizontal;
                } else {
                    mOutSideRadius -= paddingVertical;
                }
            } else if (MeasureSpec.EXACTLY == widthMode) {
                mOutSideRadius = width - paddingHorizontal;
            } else  {
                mOutSideRadius = height - paddingVertical;
            }
            mOutSideRadius *= 0.5F; // 以上计算都是正方形边长，半径需要取半；
            if (mProgressWidth < 0) setProgressWidth(PROGRESS_WIDTH);
            mInsideRadius = mOutSideRadius - mProgressWidth;
            mSwitchSideLength = (int)(mInsideRadius * 1.5F);
        } else {
            // AT_MOST or UNSPECIFIED
            if (mProgressWidth < 0) setProgressWidth(PROGRESS_WIDTH);
            if (mSwitchHeight < 0) setSwitchHeight(SWITCH_SIDE_LENGTH);
            if (mSwitchWidth < 0) setSwitchWidth(SWITCH_SIDE_LENGTH);
            mSwitchSideLength = Math.max(mSwitchHeight, mSwitchWidth);
            mInsideRadius = (float) (mSwitchSideLength / Math.sqrt(2D));
            mOutSideRadius = (float) (SWITCH_SIDE_LENGTH / Math.sqrt(2));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LUtil.d(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LUtil.d(TAG, "onDraw");

        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setAntiAlias(true);

        mPaint.setColor(mProgressDefaultColor);
        canvas.drawCircle(mOutSideRadius, mOutSideRadius, mInsideRadius, mPaint);

        mPaint.setColor(mProgressColor);
        mRectF.left = mOutSideRadius - mInsideRadius;
        mRectF.top = mOutSideRadius - mInsideRadius;
        mRectF.right = 2 * mInsideRadius + mProgressWidth;
        mRectF.bottom = 2 * mInsideRadius + mProgressWidth;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(mRectF, STATED_ANGLE, mSweepAngle, false, mPaint);
        } else {
            canvas.drawArc(mRectF.left, mRectF.top, mRectF.right, mRectF.bottom,
                    STATED_ANGLE, mSweepAngle, false, mPaint);
        }
        canvas.restore();

        Drawable drawable;
        if (mState == STATE_STARTED) {
            drawable = mSwitchStared;
        } else {
            drawable = mSwitchPaused;
        }
        int right = Math.max(drawable.getIntrinsicWidth(), mSwitchSideLength/2);
        int bottom = Math.max(drawable.getIntrinsicHeight(), mSwitchSideLength/2);
        canvas.save();
        // 截取drawable绘制区域
//        canvas.clipRect(mOutSideRadius - mInsideRadius / 2, mOutSideRadius - mInsideRadius / 2, mOutSideRadius + mInsideRadius / 2, mOutSideRadius + mInsideRadius / 2);
        drawable.setBounds(0, 0, right, bottom);
        // 将 drawable 绘制的起始位置移动到让 drawable 处在当前view正中间的位置
        canvas.translate(mOutSideRadius - right * 0.5F, mOutSideRadius - bottom * 0.5F);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        LUtil.d(TAG, "invalidate");
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
        LUtil.d(TAG, "postInvalidate");
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        LUtil.d(TAG, "requestLayout");
    }

    @Override
    public @NonNull
    String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(TAG);
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(' ').append("mEnabled=").append(isEnabled());
        sb.append(' ').append("mProgressColor=").append(getProgressColor());
        sb.append(' ').append("mProgressDefaultColor=").append(getProgressDefaultColor());
        sb.append(' ').append("mProgressWidth=").append(getProgressWidth());
        sb.append(' ').append("mSwitchHeight=").append(getSwitchHeight());
        sb.append(' ').append("mSwitchWidth=").append(getSwitchWidth());
        sb.append(' ').append('}');
        return sb.substring(0);
    }
}
