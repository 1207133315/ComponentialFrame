package com.liningkang.event_dispatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil.setContentView
import com.liningkang.base.BaseCommonActivity
import com.liningkang.utils.LogUtils

class EventDispatchActivity : BaseCommonActivity() {
    var name="EventDispatchActivity"
    /**
     * 设置layoutId
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_event_dispatch
    }


    /**
     * 初始化视图
     */
    override fun initView(savedInstanceState: Bundle?): Unit? {
        val vp1 = MyViewGroup(this, "vp1")
        vp1.addView(MyView(this, "view1"))
        vp1.addView(MyView(this, "view2"))

        val vp2 = MyViewGroup(this, "vp2")
        vp2.addView(MyView(this, "view3"))
        vp2.addView(MyView(this, "view4"))

        val parentVp = MyViewGroup(this, "parentVp")
        parentVp.addView(vp1)
        parentVp.addView(vp2)
        setContentView(parentVp)
        return null
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.d("shijian","$name  dispatchTouchEvent: ${getEventName(ev!!.action)}")
        return super.dispatchTouchEvent(ev)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.d("shijian","$name  onTouchEvent: ${getEventName(event!!.action)}")
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