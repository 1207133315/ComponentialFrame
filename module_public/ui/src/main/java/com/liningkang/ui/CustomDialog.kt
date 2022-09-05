package com.liningkang.ui

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.liningkang.base.BaseActivity
import com.liningkang.base.BaseBindingDialog
import com.liningkang.base.BaseDialog
import com.liningkang.ui.databinding.DialogCustomBinding
import kotlinx.android.synthetic.main.dialog_custom.*


class CustomDialog(activity: BaseActivity) :
    BaseBindingDialog<DialogCustomBinding>(activity, R.style.CustomDialog) {
    override fun getLayoutId(): Int = R.layout.dialog_custom

    override fun initView(binding: DialogCustomBinding) {
        tvcontent.text = "加载中"
        setCanceledOnTouchOutside(true)
        val attributes = window!!.attributes
        attributes.alpha = 0.8f
        window!!.attributes = attributes
        setCancelable(false)
    }


}