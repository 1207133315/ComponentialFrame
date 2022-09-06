package com.liningkang.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.liningkang.base.BaseApplication
import com.liningkang.base.BaseViewModel
import com.liningkang.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel<LoginApi>() {
    companion object {
        private const val TAG = "LoginViewModel"
    }


    fun requestWeatherOfFlow(city: String) = launchOnFlow(Dispatchers.Default) {
        LogUtils.d("LoginViewModel", "requestWeatherOfFlow: [${Thread.currentThread().name}]")
        request {
            emit(LoginData(city = "加载中..."))
            delay(3000)
            it.loginByVerificationCode(city)
        }.doSuccess {
            Log.i(TAG, "requestWeather:${it?.city} ")
            emit(it!!)
        }.doFailure { code, msg ->
            emit(LoginData(city = msg ?: "请求失败-$code"))
        }.doError { ex, error ->
            emit(LoginData(city = error.message))
        }.procceed()
    }


}