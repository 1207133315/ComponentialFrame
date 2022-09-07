package com.liningkang.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.common.RouteConfig
import com.liningkang.login.databinding.ActivityMain3Binding
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouteConfig.ROUTER_ACTIVITY_MAIN3)
class MainActivity3 : BaseViewModelActivity<LoginViewModel, ActivityMain3Binding>() {

    companion object {
        const val TAG = "MainActivity3"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main3
    }

    override fun initView(savedInstanceState: Bundle?) {
        text.setOnClickListener {
            Log.i(TAG, "initView: " + getForegroundActivity()?.javaClass?.simpleName)
            // finish()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("activity", "onStop: MainActivity3")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity", "onDestroy: MainActivity3")
    }
}