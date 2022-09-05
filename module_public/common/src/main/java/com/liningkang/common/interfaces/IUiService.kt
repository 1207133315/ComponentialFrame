package com.liningkang.common.interfaces

import android.app.Activity
import android.app.Dialog
import com.alibaba.android.arouter.facade.template.IProvider

interface IUiService : IProvider{
    fun createCustomDialog(activity: Activity):Dialog
}