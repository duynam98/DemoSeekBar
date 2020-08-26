package com.duynam.demoseekbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class SeekView extends View {

    private Paint paint;
    private Paint paintRect;
    private Paint painOval;
    private Bitmap bitmapDot;
    private RectF rect;

    private float dotX;
    private float dotY;
    private float dotRawX;
    private float dotRawY;
    private float deltaX;
    private float deltaY;

    private float startX = 0;
    private float startY = 0;
    private float stopX = 0;
    private float stopY = 0;

    private float leftBitmap = 0;

    public SeekView(Context context) {
        super(context);
        init();
    }

    public SeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SeekView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paintRect = new Paint();
        paintRect.setColor(Color.YELLOW);
        paintRect.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        bitmapDot = BitmapFactory.decodeResource(getResources(), R.drawable.dot);
        Bitmap bitmap = Bitmap.createBitmap(44, 44, Bitmap.Config.ARGB_8888);
        rect = new RectF();

        painOval = new Paint();
        painOval.setColor(Color.YELLOW);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(0.05f * getMeasuredHeight());
        paintRect.setStrokeWidth(0.07f * getMeasuredHeight());
        canvas.drawLine(getPaddingLeft(), 0.5f * getMeasuredHeight(), getWidth() - getPaddingRight(), 0.5f * getMeasuredHeight(), paint);
        canvas.drawLine(getPaddingLeft(), 0.3f * getMeasuredHeight(), getPaddingLeft(), 0.7f * getMeasuredHeight(), paint);
        //canvas.drawLine(0.25f * getMeasuredWidth() - getPaddingLeft(), 0.3f * getMeasuredHeight(), 0.25f * getMeasuredWidth() - getPaddingLeft(), 0.7f * getMeasuredHeight(), paint);
        canvas.drawLine(0.5f * getMeasuredWidth() - getPaddingLeft(), 0.3f * getMeasuredHeight(), 0.5f * getMeasuredWidth() - getPaddingLeft(), 0.7f * getMeasuredHeight(), paint);
        canvas.drawLine(0.75f * getMeasuredWidth() - getPaddingLeft(), 0.3f * getMeasuredHeight(), 0.75f * getMeasuredWidth() - getPaddingLeft(), 0.7f * getMeasuredHeight(), paint);
        canvas.drawLine(0.75f * getMeasuredWidth() - getPaddingLeft(), 0.3f * getMeasuredHeight(), 0.75f * getMeasuredWidth() - getPaddingLeft(), 0.7f * getMeasuredHeight(), paint);
        canvas.drawLine(getWidth() - getPaddingRight(), 0.3f * getMeasuredHeight(), getWidth() - getPaddingRight(), 0.7f * getMeasuredHeight(), paint);

        rect.top = (int) (0.5f * getMeasuredHeight());
        rect.bottom = (int) (0.5f * getMeasuredHeight());
        rect.right = getPaddingLeft() + 44;
        rect.left = getPaddingLeft();

        //canvas.drawBitmap(bitmapDot, null, rect, paint);
        //canvas.drawLine(getPaddingLeft() + startX, 0.5f * getMeasuredHeight(), stopX, 0.5f * getMeasuredHeight(), paintRect);
        canvas.drawOval(leftBitmap+getPaddingLeft()+22, 0.3f * getMeasuredHeight(), leftBitmap+44, 0.7f * getMeasuredHeight(), painOval);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 100;
        int height = 100;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.UNSPECIFIED) {
            width = 100;
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(100, MeasureSpec.getSize(widthMeasureSpec));
        }

        setMeasuredDimension(width, height);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                painOval.setColor(Color.GREEN);
                break;
            case MotionEvent.ACTION_MOVE:
//                FrameLayout.LayoutParams mParam = (FrameLayout.LayoutParams) this.getLayoutParams();
//                mParam.leftMargin = (int) deltaX;
//                mParam.rightMargin = deltaY;
                //leftBitmap = getWidth() - getPaddingLeft() - 22;
                dotX = event.getX();
                dotY = event.getY();
                dotRawX = event.getRawX();
                dotRawY = event.getRawY();
                deltaX = event.getRawX();
                deltaY = dotRawY - dotY;
                stopX = event.getX();
                leftBitmap = leftBitmap + event.getX();
                if (event.getX() < getPaddingLeft()) {
                    leftBitmap = 0;
                    stopX = 0;
                }
                if (event.getX() > 0.8f * getWidth() - getPaddingLeft()) {
                    leftBitmap = getWidth() - getPaddingRight() - 22;
                    stopX = getWidth() - getPaddingRight() + 22;
                    //stopX = getWidth() - getPaddingRight() + 22;
                }
//                if (event.getX() >= getMeasuredWidth() / 8 && event.getX() < (getMeasuredWidth() / 8) * 3) {
//                    leftBitmap = getMeasuredWidth() / 4;
//                    stopX = getMeasuredWidth() / 4;
//                } else if (event.getX() >= (getMeasuredWidth() / 8) * 3) {
//                    leftBitmap = getMeasuredWidth() / 2;
//                    stopX = getMeasuredWidth() / 2;
//                }
                Log.e("deltaX", "onTouchEvent: " + event.getX());
                break;
            case MotionEvent.ACTION_UP:
                painOval.setColor(Color.YELLOW);
                break;
        }
        invalidate();
        return true;
    }

    private void move() {

    }

}
