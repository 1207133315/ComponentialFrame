package com.liningkang.network

import java.lang.Exception

interface DataCall<T> {
    fun onSuccess(data:T)
    fun onFail(exception:ApiException)
}