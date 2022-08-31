package com.liningkang.login

import android.util.Log
import android.widget.Toast
import com.liningkang.base.BaseApplication
import com.liningkang.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel : BaseViewModel<LoginApi>() {
    companion object {
        var TAG: String = "LoginViewModel"
    }

    fun requestWeather() {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//                val loginApi = NetworkManager.create(getRequestType(), getTClass<LoginApi>())
//                val response = loginApi.login("邯郸").execute()
//                Log.i(TAG, "requestWeather:${response.body()?.data?.city} ")
//            }
//
//        }

        launchOnIO {
            val parseResult = request {
                it.loginByVerificationCode("邯郸")
            }.doSuccess {
                Toast.makeText(BaseApplication.context, "数据-${it!!.city}", Toast.LENGTH_SHORT)
                    .show()
                Log.i(TAG, "requestWeather:${it.city} ")
            }.doFailure { code, msg ->
                Toast.makeText(BaseApplication.context, "数据获取失败", Toast.LENGTH_SHORT).show()
            }.doError { ex, error ->
                Toast.makeText(BaseApplication.context, error.message, Toast.LENGTH_SHORT).show()
            }
            withContext(Dispatchers.Main){
                parseResult.procceed()
            }

        }

    }
}