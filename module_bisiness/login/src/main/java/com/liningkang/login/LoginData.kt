package com.liningkang.login

import com.google.gson.annotations.SerializedName

data class LoginData(
    @SerializedName("city")
    var city: String="",
    @SerializedName("forecast")
    var forecast: List<Forecast>?=null,
    @SerializedName("ganmao")
    var ganmao: String="",
    @SerializedName("wendu")
    var wendu: String="",
    @SerializedName("yesterday")
    var yesterday: Yesterday?=null
) {

    data class Forecast(
        var date: String,
        var fengli: String,
        var fengxiang: String,
        var high: String,
        var low: String,
        var type: String,
        var isShow:Boolean=false
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