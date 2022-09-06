package com.liningkang.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.liningkang.base.BaseApplication
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.login.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : BaseViewModelActivity<LoginViewModel, ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) = binding?.run {
        this.loginData = LoginData()
        text.setOnClickListener {
            viewModel?.requestWeatherOfFlow("北京")?.collectIn (this@MainActivity::onRequestWeather)
        }
        jump.setOnClickListener {
            startActivity(Intent(this@MainActivity, MainActivity3::class.java))
        }
    }

    private fun onRequestWeather(data: LoginData) {
        Toast.makeText(BaseApplication.context, "加载完成", Toast.LENGTH_SHORT).show()
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