package com.liningkang.event_dispatch

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager
import com.liningkang.utils.LogUtils
import kotlin.math.abs

class MyViewPager(context: Context, attributeSet: AttributeSet?) :
    ViewPager(context, attributeSet) {
    constructor(context: Context) : this(context, null)

    var downX = 0
    var downY = 0
    var isIntercept = false
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            super.onInterceptTouchEvent(ev)
            return false
        }
        return true
    }
}