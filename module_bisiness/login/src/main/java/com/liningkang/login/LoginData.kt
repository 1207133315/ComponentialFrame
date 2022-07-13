package com.liningkang.login

import com.google.gson.annotations.SerializedName

class LoginData(
    var city: String,
    var forecast: List<Forecast>,
    var ganmao: String,
    var wendu: String,
    var yesterday: Yesterday
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