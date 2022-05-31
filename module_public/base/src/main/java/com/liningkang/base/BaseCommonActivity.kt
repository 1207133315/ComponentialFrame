package com.liningkang.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @description 普通Activity基类
 */
abstract class BaseCommonActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView(savedInstanceState)
    }




}