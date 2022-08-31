package com.liningkang.login

import com.liningkang.network.DataResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginApi {

    @GET("weather_mini")
    suspend fun loginByVerificationCode(@Query("city") city: String):DataResponse<LoginData>

}