package com.liningkang.base

import androidx.lifecycle.*
import com.liningkang.network.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.lang.String
import java.lang.reflect.ParameterizedType
import kotlin.Any
import kotlin.Int

open class BaseViewModel<R> : ViewModel(), LifecycleEventObserver {
    val iRequest: R

    init {
        iRequest = NetworkManager.create(getRequestType(), getTClass())
    }

    /**
     * 此方法用来确定采用的Rtrofit中baseUrl
     * 由于Retrofit特性，baseUrl不能随意改动，当大项目拥有
     * 多个域名控制不同业务的时候，则需要不同的Retrofit
     * @TODO 可以根据需要在子类中重写
     */
    protected open fun getRequestType(): Int {
        return NetworkConfig.REQUEST_TYPE_DEFAULT
    }


    protected open fun start() {
    }


    protected open fun resume() {
    }


    protected open fun pause() {
    }


    protected open fun stop() {
    }


    protected open fun destroy() {
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_START -> {
                start()
            }
            Lifecycle.Event.ON_RESUME -> {
                resume()
            }
            Lifecycle.Event.ON_PAUSE -> {
                pause()
            }
            Lifecycle.Event.ON_STOP -> {
                stop()
            }
            Lifecycle.Event.ON_DESTROY -> {
                destroy()
            }
        }
    }

    /**
     * 请求数据，所有网络操作请使用本方法
     * @param observable
     * @param dataCall
     * @return
     */
    fun <T : Any> request(
        observable: Observable<DataResponse<T>>,
        dataCall: DataCall<T>,
    ): Disposable? {
        return observable.subscribeOn(Schedulers.io()) //将请求调度到子线程上
            .observeOn(AndroidSchedulers.mainThread()) //观察响应结果，把响应结果调度到主线程中处理
            .onErrorReturn { t ->
              null
            }
            .subscribe(getConsumer(dataCall)
            ) { t -> dataCall.onFail(ApiException.handleException(t)) }
    }


    /**
     * @author: yintao
     * @date: 2020/4/20 11:56 PM
     * @method
     * @param
     * @return
     * @description 根据返回值{@link #getResponseType()}灵活改变Consumer或者自己直接重写都可以
     */
    protected open fun <T> getConsumer(dataCall: DataCall<T>): Consumer<DataResponse<T>>? {
        return if (getResponseType() == NetworkConfig.RESPONSE_TYPE_SDK_BD) { //如果整个项目中只有一个百度的接口，那么不建议修改基类Presenter，请重写getConsumer方法就可以。
            Consumer<DataResponse<T>> { result ->
                if (result.code === 0) {
                    dataCall.onSuccess(result.data as T)
                } else {
                    dataCall.onFail(ApiException(String.valueOf(result.code), result.msg))
                }
            }
        } else {
            Consumer<DataResponse<T>> { result ->
                if (result.code == 0) {
                    dataCall.onSuccess(result.data as T)
                } else {
                    dataCall.onFail(ApiException(String.valueOf(result.code), result.msg))
                }
            }
        }
    }

    /**
     * 获取泛型对相应的Class对象
     * @return
     */
    private fun getTClass(): Class<R>? {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        val type = this.javaClass.genericSuperclass as ParameterizedType
        //返回表示此类型实际类型参数的 Type 对象的数组()，想要获取第二个泛型的Class，所以索引写1
        return type.actualTypeArguments[0] as Class<R> //<T>
    }

    /**
     * 返回值类型，方便不同接口返回数据结构不同的情况，参见[.getConsumer]
     * 应对大项目多数据结构
     * @TODO 可以根据需要在子类中重写
     */
    private fun getResponseType(): Int {
        return NetworkConfig.RESPONSE_TYPE_DEFAULT
    }


}