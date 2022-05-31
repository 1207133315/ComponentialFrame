package com.liningkang.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @description 所有基类Activity的基类,主要便于扩展,一般Activity都不继承本类,而是继承这个类的子类
 */
 abstract class  BaseActivity : AppCompatActivity() {
    /**
     * 记录处于前台的Activity
     */
    private var mForegroundActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onStart() {
        super.onStart()
        mForegroundActivity = this
    }

    /**
     * 获取当前处于前台的activity
     */
    fun getForegroundActivity(): BaseActivity? {
        return mForegroundActivity
    }

    /**
     * 设置layoutId
     *
     * @return
     */
    protected abstract fun getLayoutId(): Int
    /**
     * 初始化视图
     */
    protected abstract fun initView(savedInstanceState: Bundle?)
}