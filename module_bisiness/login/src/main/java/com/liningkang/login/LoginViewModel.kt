package com.liningkang.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.liningkang.base.BaseApplication
import com.liningkang.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel<LoginApi>() {
    companion object {
        var TAG: String = "LoginViewModel"
    }

    val dataLiveData = MutableLiveData<LoginData>()


    fun requestWeatherOfFlow(city: String) = flow {
        val parseResult = request {
            delay(3000)
            it.loginByVerificationCode(city)
        }.doSuccess {
            Log.i(TAG, "requestWeather:${it?.city} ")
            emit(it!!)
        }.doFailure { code, msg ->
            emit(LoginData())
        }.doError { ex, error ->
            emit(LoginData())
        }
        parseResult.procceed()

    }.flowOn(Dispatchers.IO)

    fun requestWeather() = launchOnIO {
        val parseResult = request {
            delay(3000)
            it.loginByVerificationCode("北京")
        }.doSuccess {
            Toast.makeText(BaseApplication.context, "数据-${it!!.city}", Toast.LENGTH_SHORT)
                .show()
            Log.i(TAG, "requestWeather:${it.city} ")
            dataLiveData.value = it
        }.doFailure { code, msg ->
            Toast.makeText(BaseApplication.context, "数据获取失败:$msg", Toast.LENGTH_SHORT).show()
            dataLiveData.value = null
        }.doError { ex, error ->
            Toast.makeText(BaseApplication.context, error?.message, Toast.LENGTH_SHORT).show()
            dataLiveData.value = null
        }
        // 回调时切回到主线程
        withContext(Dispatchers.Main) {
            parseResult.procceed()
        }
    }


}