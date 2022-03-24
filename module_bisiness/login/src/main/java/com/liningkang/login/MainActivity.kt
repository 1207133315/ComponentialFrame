package com.liningkang.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.liningkang.base.BaseActivity
import com.liningkang.login.databinding.ActivityMainBinding

class MainActivity : BaseActivity<LoginViewModel,ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        viewModel?.request()
    }

}