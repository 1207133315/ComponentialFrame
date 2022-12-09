package com.liningkang.im

import com.liningkang.base.BaseApplication
import com.liningkang.im.IMHelper
import com.liningkang.utils.LogUtils

class IMApplication:BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        IMHelper.init("1161221115120685#myim")
        IMHelper.createAccount("liningkang","123456"){
            LogUtils.i("创建账号成功")
        }
    }
}