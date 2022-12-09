package com.liningkang.login.adapter

import android.content.Context
import com.hnquxing.home_main.common.BaseDatabindingRecyclerAdapter
import com.liningkang.base.DatabindingViewHolder
import com.liningkang.login.LoginData
import com.liningkang.login.R
import com.liningkang.login.databinding.ListItemBinding

class ForecastAdapter(context: Context) :
    BaseDatabindingRecyclerAdapter<ListItemBinding, LoginData.Forecast>(context) {
    override fun getItemLayoutId(): Int {
        return R.layout.list_item
    }

    override fun onBind(holder: DatabindingViewHolder<ListItemBinding>, position: Int) {

    }
}