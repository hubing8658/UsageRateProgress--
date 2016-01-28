
package com.iss.usagerateprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆形使用率的进度条
 * 
 * @author hubing
 * @version 1.0.0 2016-1-26
 */

public class UsageRateProgress extends View {

    /** 进度名称 */
    private String mName = "CPU";

    /** 进度名称文本颜色 */
    private int mNameTextColor = Color.WHITE;

    /** 进度条剩余进度背景颜色 */
    private int mProgressResidueColor = 0xffe5e7f1;

    /** 进度条颜色 */
    private int mProgressColor = 0xffb3b7dd;

    /** 内环圆颜色 */
    private int mInsideCircleColor = 0xffcbcbcb;

    /** 外圆环进度宽 */
    private int mOutsideProgressWidth;

    /** 内圆环进度宽 */
    private int mInsideProgressWidth;

    /** 最大进度 */
    private int mMaxProgress = 100;

    /** 当前进度 */
    private int mProgress = 30;

    /** 画笔对象 */
    private Paint mPaint;

    /** 外圆环矩形显示区域 */
    private RectF mOutsideRect;

    /** 内圆环矩形显示区域 */
    private RectF mInsideRect;

    /** 内圆环矩形上的文字显示区域 */
    private RectF mInsideTextRect;

    /** 控件宽 */
    private int mWidth;

    /** 控件高 */
    private int mHeight;

    /** 进度名称文字矩形区域高 */
    private int mTextRectHeight;

    public UsageRateProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化变量数据
     * 
     * @author hubing
     */
    private void init() {
        // 初始化画笔对象
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mOutsideRect = new RectF();
        mInsideRect = new RectF();
        mInsideTextRect = new RectF();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.mWidth = getMeasuredWidth();
        this.mHeight = getMeasuredHeight();

        mOutsideProgressWidth = mWidth / 10;
        mInsideProgressWidth = mWidth / 70;

        // 计算外进度条矩形区域
        int l = mOutsideProgressWidth / 2;
        int t = mOutsideProgressWidth / 2;
        int b = mHeight - t;
        int r = mWidth - l;
        mOutsideRect.set(l, t, r, b);

        // 计算内进度条矩形区域
        l = mOutsideProgressWidth * 2 + mInsideProgressWidth / 2;
        t = mOutsideProgressWidth * 2 + mInsideProgressWidth / 2;
        b = mHeight - t;
        r = mWidth - l;
        mInsideRect.set(l, t, r, b);

        // 计算内进度条上显示的文字矩形区域
        mTextRectHeight = mWidth / 8;
        l = (int) (mInsideRect.left + (mInsideRect.right - mInsideRect.left) / 6);
        t = (int) (mInsideRect.bottom - mTextRectHeight * 3 / 4);
        b = (int) (mInsideRect.bottom + mTextRectHeight / 4);
        r = mWidth - l;
        mInsideTextRect.set(l, t, r, b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画笔样式，用以画圆环
        mPaint.setStyle(Style.STROKE);
        drawOutsideCirlce(canvas);
        drawInsideCirlce(canvas);
        
        // 设置画笔样式，用以画文本
        mPaint.setStyle(Style.FILL);
        drawProgressName(canvas);
        drawProgressText(canvas);
    }

    /**
     * 画百分比进度文本
     * 
     * @param canvas
     * @author hubing
     */
    private void drawProgressText(Canvas canvas) {
        String text = String.valueOf(mProgress);
        int textSize = mWidth / 6;
        mPaint.setColor(mProgressColor);
        mPaint.setTextSize(textSize);
        float textWidth = mPaint.measureText(text);
        float x = mWidth / 2 - textWidth / 2;
        float y = mHeight / 2 + (Math.abs(mPaint.ascent()) - Math.abs(mPaint.descent())) / 2;
        canvas.drawText(text, x, y, mPaint);
        
        // 画百分比符号
        int percentTextSize = mWidth / 10;
        mPaint.setTextSize(percentTextSize);
        text = "%";
        x = mWidth / 2 + textWidth / 2;
        canvas.drawText(text, x, y, mPaint);
    }

    /**
     * 画内环上显示的进度名称
     * 
     * @param canvas
     * @author hubing
     */
    private void drawProgressName(Canvas canvas) {
        // 画进度名称的圆角矩形
        canvas.drawRoundRect(mInsideTextRect, mTextRectHeight / 2, mTextRectHeight / 2, mPaint);

        // 画进度名称
        mPaint.setColor(mNameTextColor);
        int textSize = mTextRectHeight * 8 / 10;
        mPaint.setTextSize(textSize);
        float textWidth = mPaint.measureText(mName);
        float x = mInsideTextRect.left + (mInsideTextRect.right - mInsideTextRect.left) / 2 - textWidth / 2;
        float y = mInsideTextRect.top + mTextRectHeight / 2 + (Math.abs(mPaint.ascent()) - Math.abs(mPaint.descent())) / 2;
        canvas.drawText(mName, x, y, mPaint);
    }

    /**
     * 画外部圆环进度
     * 
     * @param canvas
     * @author hubing
     */
    private void drawOutsideCirlce(Canvas canvas) {
        mPaint.setStrokeWidth(mOutsideProgressWidth);

        // 画背景
        mPaint.setColor(mProgressResidueColor);
        canvas.drawArc(mOutsideRect, -90, 360, false, mPaint);

        // 画当前进度
        mPaint.setColor(mProgressColor);
        float sweepAngle = mProgress * 1.0F / mMaxProgress * 360;
        canvas.drawArc(mOutsideRect, -90, sweepAngle, false, mPaint);
    }

    /**
     * 画内部圆环进度
     * 
     * @param canvas
     * @author hubing
     */
    private void drawInsideCirlce(Canvas canvas) {
        mPaint.setStrokeWidth(mInsideProgressWidth);
        mPaint.setColor(mInsideCircleColor);
        canvas.drawArc(mInsideRect, -90, 360, false, mPaint);
    }

    /**
     * 设置当前进度,在UI线程中调用
     * 
     * @param progress
     * @author hubing
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > mMaxProgress) {
            progress = mMaxProgress;
        }
        this.mProgress = progress;
        this.invalidate();
    }

    /**
     * 设置当前进度,在非UI线程中调用
     * 
     * @param progress
     * @author hubing
     */
    public void setPostProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        } else if (progress > mMaxProgress) {
            progress = mMaxProgress;
        }
        this.mProgress = progress;
        this.postInvalidate();
    }

    /**
     * 设置进度名称
     * 
     * @param name
     * @author hubing
     */
    public void setName(String name) {
        this.mName = name;
        this.invalidate();
    }

}
