package com.example.yuanting.signalviewactivity.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.yuanting.signalviewactivity.R;


/**
 * 自定义信号view
 * 格数
 * 有效信号颜色
 */
public class SignalView1 extends View {
    private int signOffset = 10;
    private int signalValue;
    private Paint paint;
    private int signalHeight;
    private int signalWidth;
    private int signalCount;
    private int signalColor;
    private int defaultSignalColor;

    public SignalView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SignalView);
        signalCount = ta.getInt(R.styleable.SignalView_signalCount,5);
        signalColor = ta.getColor(R.styleable.SignalView_signalColor,getResources().getColor(R.color.color_green));
        defaultSignalColor = getResources().getColor(R.color.black3);
        ta.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setSignalValue(int signalValue){
        this.signalValue = signalValue;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        signalHeight = getHeight();
        signalWidth = getWidth() / signalCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = 0;
        int height = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            width = specSize;
            height = specSize;
        }else{
            width = 60;
            if(specMode == MeasureSpec.AT_MOST){
                width = Math.min(width,specSize);
                height = Math.min(height,specSize);
            }
        }
        signalHeight = height;
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0;i < signalCount ; i++){
            paint.setStyle(Paint.Style.FILL);
            if(i < signalValue){
                paint.setColor(signalColor);

            }else{
                paint.setColor(defaultSignalColor);
            }
            canvas.drawRect(
                    (float) (signalWidth * i + signOffset),
                    (float)(signalHeight * (signalCount - i) * 0.2),
                    (float)(signalWidth * (i + 1)),
                    signalHeight,paint);
        }
    }
}
