package com.example.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.peiwang.R;

public class HwLoadingView extends View {
    private static final int CIRCLE_COUNT = 12;  //小圆总数
    private static final int DEGREE_PER_CIRCLE = 360 / CIRCLE_COUNT;  //小圆圆心之间间隔角度差
    private float[] mWholeCircleRadius = new float[CIRCLE_COUNT];  //记录所有小圆半径
    private int[] mWholeCircleColors = new int[CIRCLE_COUNT]; //记录所有小圆颜色
    private float mMaxCircleRadius;   //小圆最大半径
    private int mSize;     //控件大小
    private int mColor;    //小圆颜色
    private Paint mPaint; //画笔
    private ValueAnimator mAnimator;
    private int mAnimateValue = 0;
    private long mDuration;   //动画时长

    public HwLoadingView(Context context) {
        this(context, null);
    }

    public HwLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HwLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HwLoadingView);
        mSize = (int) typedArray.getDimension(R.styleable.HwLoadingView_hlv_size, dp2px(context, 100));
        setSize(mSize);

        mColor = typedArray.getColor(R.styleable.HwLoadingView_hlv_color, Color.parseColor("#333333"));
        setColor(mColor);

        mDuration = typedArray.getInt(R.styleable.HwLoadingView_hlv_duration, 15000);
        typedArray.recycle();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);
    }

    /**
     * 初始化所有小圆半径、颜色
     */
    private void initValue() {
        float minCircleRadius = mSize / 24;
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            switch (i) {
                case 7:
                    mWholeCircleRadius[i] = minCircleRadius * 1.25f;
                    mWholeCircleColors[i] = (int) (255 * 0.7f);
                    break;
                case 8:
                    mWholeCircleRadius[i] = minCircleRadius * 1.5f;
                    mWholeCircleColors[i] = (int) (255 * 0.8f);
                    break;
                case 9:
                case 11:
                    mWholeCircleRadius[i] = minCircleRadius * 1.75f;
                    mWholeCircleColors[i] = (int) (255 * 0.9f);
                    break;
                case 10:
                    mWholeCircleRadius[i] = minCircleRadius * 2f;
                    mWholeCircleColors[i] = 255;
                    break;
                default:
                    mWholeCircleRadius[i] = minCircleRadius;
                    mWholeCircleColors[i] = (int) (255 * 0.5f);
                    break;
            }
        }
        mMaxCircleRadius = minCircleRadius * 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //去除wrap_content、Match_Parent对控件的影响
        setMeasuredDimension(mSize, mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mSize>0){
            //每隔DEGREE_PER_CIRCLE * mAnimateValue角度，绘制所有小圆
            canvas.rotate(DEGREE_PER_CIRCLE*mAnimateValue,mSize/2,mSize/2);
            for (int i = 0; i < CIRCLE_COUNT; i++) {
                //设置小圆颜色
                mPaint.setAlpha(mWholeCircleColors[i]);
                //每隔DEGREE_PER_CIRCLE角度，绘制一个小圆
                canvas.drawCircle(mSize / 2, mMaxCircleRadius, mWholeCircleRadius[i], mPaint);
                canvas.rotate(DEGREE_PER_CIRCLE, mSize / 2, mSize / 2);
            }
        }
    }

    /**
     * 设置控件大小
     */
    public void setSize(int size) {
        mSize = size;
        invalidate();
    }

    /**
     * 设置小圆颜色
     */
    public void setColor(@ColorInt int color) {
        mColor = color;
        invalidate();
    }

    /**
     * dp转px
     */
    private int dp2px(Context context, float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }


}
