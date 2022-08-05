package com.liningkang.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.os.Process
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

open class BaseApplication : Application() {
    companion object {
        /**
         * 主线程ID
         */
         var mMainThreadId = -1

        /**
         * 主线程ID
         */
         var mMainThread: Thread? = null

        /**
         * 主线程Handler
         */
         var mMainThreadHandler: Handler? = null

        /**
         * 主线程Looper
         */
         var mMainLooper: Looper? = null


        /**
         * context 全局唯一的上下文
         */
         var context: Context? = null

         var sharedPreferences: SharedPreferences? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        mMainThreadId = Process.myTid()
        mMainThread = Thread.currentThread()
        mMainThreadHandler = Handler()
        mMainLooper = mainLooper
        sharedPreferences =
            getSharedPreferences("share.xml", MODE_PRIVATE)

        MMKV.initialize(this)
        if (BuildConfig.DEBUG){
            ARouter.openLog() // Print log
            ARouter.openDebug()
        }
        ARouter.init(this) //阿里路由初始化

    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

}