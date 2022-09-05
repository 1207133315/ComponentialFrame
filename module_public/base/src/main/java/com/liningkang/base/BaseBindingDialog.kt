package com.liningkang.base

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingDialog<VDB : ViewDataBinding>(activity: BaseActivity, style: Int=0) : BaseDialog(activity, style) {

    init {
        var binding = DataBindingUtil.inflate<VDB>(
            LayoutInflater.from(activity),
            getLayoutId(),
            null,
            false
        )
        setContentView(binding.root)
        initView(binding)
    }

    abstract fun getLayoutId(): Int
    abstract fun initView(binding:VDB)
}