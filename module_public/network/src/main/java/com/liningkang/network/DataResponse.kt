package com.liningkang.network

class DataResponse<T>(
    var code: Int,
    var status: Int,
    var msg: String,
    var data: T?
) {

    fun isSuccess(): Boolean {
        return status == NetworkConfig.HTTP_OK
    }
}