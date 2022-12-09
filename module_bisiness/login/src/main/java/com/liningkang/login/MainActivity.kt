package com.liningkang.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.liningkang.base.BaseApplication
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.common.RouteConfig
import com.liningkang.common.interfaces.IUiService
import com.liningkang.login.adapter.ForecastAdapter
import com.liningkang.login.databinding.ActivityMainBinding
import com.liningkang.utils.LogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

@Route(path = RouteConfig.ROUTER_ACTIVITY_MAIN)
class MainActivity : BaseViewModelActivity<LoginViewModel, ActivityMainBinding>() {
    val dialog = (ARouter.getInstance().build(RouteConfig.ROUTER_SERVICE_UI)
        .navigation() as IUiService?)

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) = binding?.run {
        loginData = LoginData(city = "点击获取城市")
        text.setOnClickListener {
            viewModel?.requestWeatherOfFlow("北京")?.collectIn(this@MainActivity::onRequestWeather)
        }
        jump.setOnClickListener {
            ARouter.getInstance().build(RouteConfig.ROUTER_ACTIVITY_MAIN3).navigation()
        }

    }

    private fun onRequestWeather(data: LoginData) {
        LogUtils.d("MainActivity", "onRequestWeather: [${data?.city}]")
        binding?.loginData = data
    }


    override fun onResume() {
        super.onResume()
        Log.i("activity", "onResume: MainActivity")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("activity", "onNewIntent: MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.i("activity", "onStop: MainActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity", "onDestroy: MainActivity")
    }


}