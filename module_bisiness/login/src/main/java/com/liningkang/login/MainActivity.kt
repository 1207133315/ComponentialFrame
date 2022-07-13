package com.liningkang.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.login.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<LoginViewModel, ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        Log.i("activity", "initView: MainActivity")
        viewModel?.requestWeather()
        binding?.text?.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        recycler.layoutManager=LinearLayoutManager(this)
        val myAdapter = MyAdapter(this)
        recycler.adapter=myAdapter

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