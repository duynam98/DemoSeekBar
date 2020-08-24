package com.duynam.demoseekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.SeekBar;

import androidx.annotation.RequiresApi;


@SuppressLint("AppCompatCustomView")
public class DemoSeekBar extends SeekBar{

    private int[] position;
    private int seekBar_width;
    private int seekBar_height;
    private int step;
    private Paint mPaint0;
    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;
    private Rect rect;
    private Bitmap bitmapDot;
    private int minValue, maxValue;
    private int defaultMin = 0;
    private int defaultMax = 100;
    private OnSeekBarChangeListener onSeekBarChangeListener;

    public DemoSeekBar(Context context) {
        super(context);
        init(context, null);
    }

    public DemoSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DemoSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DemoSeekBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint0 = new Paint();
        mPaint1 = new Paint();
        mPaint2 = new Paint();
        mPaint3 = new Paint();
        mPaint0.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint1.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint2.setColor(getResources().getColor(R.color.colorPrimary));
        mPaint3.setColor(getResources().getColor(R.color.colorPrimary));
        bitmapDot = BitmapFactory.decodeResource(getResources(), R.drawable.dot);
        position = new int[] {0, 30, 60, 90};
        setPadding(0, 16, 0, 16);
        setMax(90);
//        if (attrs != null) {
//            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DemoSeekBar, 0, 0);
//            minValue = typedArray.getInteger(R.styleable.DemoSeekBar_min, defaultMin);
//            maxValue = typedArray.getInteger(R.styleable.DemoSeekBar_max, defaultMax);
//        }
        //setOnSeekBarChangeListener(this);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        seekBar_width = getMeasuredWidth();
        seekBar_height = getMeasuredHeight();
        float temp = seekBar_width / 12;
        //canvas.drawRect(new Rect(getPaddingLeft(), 0, getPaddingLeft() + 10, seekBar_height), mPaint);
        for (int i = 0; i < position.length; i++) {
            canvas.drawRect(position[i]*temp, 0, position[i]*temp+5, seekBar_height, getPaint(i));
        }
    }

    public Paint getPaint(int pos){
        switch (pos){
            case 0:
                return mPaint0;
            case 1:
                return mPaint1;
            case 2:
                return mPaint2;
            case 3:
                return mPaint3;
        }
        return null;
    }

    public void setColorPain0(){
        mPaint0.setColor(getResources().getColor(R.color.colorAccent));
        invalidate();
    }

    public void setColorPain1(){
        mPaint1.setColor(getResources().getColor(R.color.colorAccent));
        invalidate();
    }

    public void setColorPain2(){
        mPaint2.setColor(getResources().getColor(R.color.colorAccent));
        invalidate();
    }

    public void setColorPain3(){
        mPaint3.setColor(getResources().getColor(R.color.colorAccent));
        invalidate();
    }

    public void defaultPain(){
        //mPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        invalidate();
    }


    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int desiredWidth = 100;
//        int desiredHeight = 100;
//
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width = 200, height = 5;
//
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthSize;
//        } else if (widthMode == MeasureSpec.AT_MOST) {
//            width = Math.min(desiredWidth, widthSize);
//        } else if (widthMode == MeasureSpec.UNSPECIFIED) {
//            width = desiredWidth;
//        }
//
//        if (heightMode == MeasureSpec.EXACTLY) {
//            height = heightSize;
//        } else if (heightMode == MeasureSpec.AT_MOST) {
//            Math.min(height, heightSize);
//        } else if (heightMode == MeasureSpec.UNSPECIFIED) {
//            height = desiredHeight;
//        }
//
//        setMeasuredDimension(width, height);
//        setPadding(0, 100, 0, 200);
//    }


}
