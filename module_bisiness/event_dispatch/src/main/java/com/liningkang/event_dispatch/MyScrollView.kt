package com.liningkang.event_dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.liningkang.utils.LogUtils
import kotlin.math.abs

class MyScrollView(context: Context, attributeSet: AttributeSet?, defStyle: Int) :
    ScrollView(context, attributeSet, defStyle) {
    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context) : this(context, null)

    var mLastX = 0
    var mLastY = 0
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        var x = (ev?.x)?.toInt()
        var y = (ev?.y)?.toInt()
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 父容器不能拦截我
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                if (abs(x!! - mLastX) > abs(y!! - mLastY)) {
                    LogUtils.d("是横向滑动")
                    //  父组件(viewpager)可以拦截我
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
            MotionEvent.ACTION_UP -> {}
            else -> {}
        }
        mLastX = x!!
        mLastY = y!!

        return super.dispatchTouchEvent(ev)
    }


}