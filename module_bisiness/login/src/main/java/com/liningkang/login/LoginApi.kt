package com.liningkang.login

import com.liningkang.network.DataResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("weather_mini")
    fun loginByVerificationCode(@Query("city")city:String):Observable<DataResponse<LoginData>>
}