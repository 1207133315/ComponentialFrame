package com.liningkang.login

import com.google.gson.annotations.SerializedName

data class LoginData(
    var city: String="点击获取城市",
    var forecast: List<Forecast>?=null,
    var ganmao: String="",
    var wendu: String="",
    var yesterday: Yesterday?=null
) {

    data class Forecast(
        var date: String,
        var fengli: String,
        var fengxiang: String,
        var high: String,
        var low: String,
        var type: String
    )

    data class Yesterday(
        var date: String,
        var fl: String,
        var fx: String,
        var high: String,
        var low: String,
        var type: String
    )
}