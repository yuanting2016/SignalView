package com.example.yuanting.signalviewactivity.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.yuanting.signalviewactivity.R


/**
 * 自定义信号view
 * 格数
 * 有效信号颜色
 */
class SignalView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null
) : View(context, attrs){
    private var signOffset = 10
    private var signalValue = 0
    private var paint: Paint
    private var signalHeight = 0
    private var signalWidth = 0
    private var signalCount = 0
    private var signalColor = 0
    private var defaultSignalColor = 0

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SignalView).apply {
            signalCount = getInt(R.styleable.SignalView_signalCount, 5)
            signalColor = getColor(R.styleable.SignalView_signalColor, resources.getColor(R.color.color_green))
            defaultSignalColor = resources.getColor(R.color.black3)
            recycle()
        }
        paint = Paint()
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.FILL
        }
    }

    fun setSignalValue(signalValue: Int) {
        this.signalValue = signalValue
        invalidate()
    }

    fun setSignalColor(color:Int){
        this.signalColor = color
        invalidate()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        signalHeight = height
        signalWidth = width / signalCount
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = 0
        var height = 0
        val specMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val specSize = View.MeasureSpec.getSize(widthMeasureSpec)

        if (specMode == View.MeasureSpec.EXACTLY) {
            width = specSize
            height = specSize
        } else {
            width = 60
            if (specMode == View.MeasureSpec.AT_MOST) {
                width = Math.min(width, specSize)
                height = Math.min(height, specSize)
            }
        }
        signalHeight = height
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until signalCount) {
            if (i < signalValue) {
                paint.color = signalColor

            } else {
                paint.color = defaultSignalColor
            }
            canvas.drawRect(
                    (signalWidth * i + signOffset).toFloat(),
                    (signalHeight * (signalCount - i) * 0.2).toFloat(),
                    (signalWidth * (i + 1)).toFloat(),
                    signalHeight.toFloat(), paint)
        }
    }

}
