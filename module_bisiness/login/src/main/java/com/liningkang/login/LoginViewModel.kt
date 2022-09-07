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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel<LoginApi>() {
    companion object {
        private const val TAG = "LoginViewModel"
    }


    fun requestWeatherOfFlow(city: String) = launchOnFlow<LoginData>(Dispatchers.Default) {
        LogUtils.d("LoginViewModel", "requestWeatherOfFlow: [当前线程:${Thread.currentThread().name}]")
        val parseResult = request {
            emit(LoginData(city = "加载中..."))
            delay(3000)
            it.loginByVerificationCode(city)
        }.doSuccess {
            emit(it!!)
        }.doFailure { code, msg ->
            emit(LoginData(city = msg ?: "请求失败-$code"))
        }.doError { ex, error ->
            emit(LoginData(city = error.message))
        }
        parseResult.procceed()
    }.map {
        if (it.city == "北京") {
            it.city = "上海"
        }
        it
    }.filter {
        it.city != "北京"
    }.catch {
        LogUtils.d("LoginViewModel", "requestWeatherOfFlow: [发现异常]")
        emit(LoginData(city = "发现异常"))
    }


}