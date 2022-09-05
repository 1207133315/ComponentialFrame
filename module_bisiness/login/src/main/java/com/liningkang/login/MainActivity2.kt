package com.liningkang.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.login.databinding.ActivityMain2Binding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : BaseViewModelActivity<LoginViewModel,ActivityMain2Binding>() {
    override fun onResume() {
        super.onResume()
        Log.i("activity", "onResume: MainActivity2")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.i("activity", "onNewIntent: MainActivity2")
    }
    override fun onStop() {
        super.onStop()
        Log.i("activity", "onStop: MainActivity2")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("activity", "onDestroy: MainActivity2")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun initView(savedInstanceState: Bundle?) {
        text.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}