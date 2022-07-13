package com.liningkang.login

import android.util.Log
import com.liningkang.base.BaseViewModel
import com.liningkang.network.ApiException
import com.liningkang.network.DataCall

class LoginViewModel : BaseViewModel<LoginApi>() {
    companion object {
        var TAG: String = this.javaClass.simpleName
    }

     fun requestWeather() {
        request(iRequest.loginByVerificationCode("邯郸"), object : DataCall<LoginData> {
            override fun onSuccess(data: LoginData) {
                Log.i(TAG, "onSuccess: ${data.city}")
            }

            override fun onFail(exception: ApiException) {

            }
        })
    }
}