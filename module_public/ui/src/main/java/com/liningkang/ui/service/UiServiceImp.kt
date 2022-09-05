package com.liningkang.ui.service

import android.app.Activity
import android.app.Dialog
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.liningkang.base.BaseActivity
import com.liningkang.common.RouteConfig
import com.liningkang.common.interfaces.IUiService
import com.liningkang.ui.CustomDialog

@Route(path = RouteConfig.ROUTER_SERVICE_UI)
class UiServiceImp : IUiService {

    override fun createCustomDialog(activity: Activity): Dialog {
        return CustomDialog(activity as BaseActivity)
    }

    /**
     * Do your init work in this method, it well be call when processor has been load.
     *
     * @param context ctx
     */
    override fun init(context: Context?) {

    }
}