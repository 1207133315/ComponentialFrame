package com.liningkang.event_dispatch

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import com.liningkang.utils.LogUtils

class MyView(context: Context, name:String?) : View(context) {
    val name=name
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("shijian","$name  dispatchTouchEvent: ${getEventName(ev!!.action)}")
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("shijian","$name  onTouchEvent: ${getEventName(event!!.action)}")
        if(name=="view3"){
            return true
        }
        return super.onTouchEvent(event)
    }


    fun getEventName(action:Int):String?{
        return when(action){
            MotionEvent.ACTION_DOWN->"ACTION_DOWN"
            MotionEvent.ACTION_MOVE->"ACTION_MOVE"
            MotionEvent.ACTION_UP->"ACTION_UP"
            MotionEvent.ACTION_CANCEL->"ACTION_CANCEL"
            else->"其他事件"
        }
    }

}