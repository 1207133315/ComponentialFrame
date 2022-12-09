package com.liningkang.im

import androidx.lifecycle.viewModelScope
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMOptions
import com.liningkang.base.BaseApplication.Companion.context
import internal.org.java_websocket.framing.b.o
import kotlinx.coroutines.*

object IMHelper {
    private var OnCompleteListener: ((state: Int) -> Unit)? = null
    fun init(appKey: String) {
        var options: EMOptions = EMOptions();
        options.appKey = appKey
        // 其他 EMOptions 配置。
        // 设置是否自动接受加好友邀请,默认是true
        options.acceptInvitationAlways = false
        // 设置是否需要接受方已读确认
        options.requireAck = true
        // 设置是否需要接受方送达确认,默认false
        options.requireDeliveryAck = false
//        //设置fpa开关，默认false
//
//        options.setFpaEnable(true)

        EMClient.getInstance().init(context, options)
    }

    fun createAccount(account: String, password: String, onComplete: ((state: Int) -> Unit)?) {
        OnCompleteListener = onComplete
        GlobalScope.launch(Dispatchers.IO) {
            // 同步方法，会阻塞当前线程。
            try {
                EMClient.getInstance().createAccount(account, password)
            }catch (err:Throwable){
                err.printStackTrace()
            }finally {
                withContext(Dispatchers.Main){
                    onComplete?.invoke(1)
                }
            }
        }.start()
    }

    fun login(account: String, password: String, emCallBack: EMCallBack?) {
        EMClient.getInstance().login(account, password, object : EMCallBack {
            /**
             * \~chinese
             * 程序执行成功时执行回调函数。
             *
             * \~english
             *
             */
            override fun onSuccess() {
                emCallBack?.onSuccess()
            }

            /**
             * \~chinese
             * 发生错误时调用的回调函数  @see EMError
             *
             * @param code           错误代码
             * @param error          包含文本类型的错误描述。
             *
             * \~english
             * Callback when encounter error @see EMError
             *
             * @param code           error code
             * @param error          contain description of error
             */
            override fun onError(code: Int, error: String?) {
                emCallBack?.onError(code, error)
            }

            /**
             * \~chinese
             * 刷新进度的回调函数
             *
             * @param progress       进度信息
             * @param status         包含文件描述的进度信息, 如果SDK没有提供，结果可能是"", 或者null。
             *
             * \~english
             * callback for progress update
             *
             * @param progress
             * @param status         contain progress description. May be empty string "" or null.
             */
            override fun onProgress(progress: Int, status: String?) {
                emCallBack?.onProgress(progress, status)
            }
        })
    }


    /**
     * @description 发送一条消息
     * @param content:发送的内容,toChatUsername:对方账号
     * @return
     */
    fun sendMessage(content: String, toChatUsername: String) {
        var message = EMMessage.createTxtSendMessage(content, toChatUsername)
        // 发送消息
        EMClient.getInstance().chatManager().sendMessage(message)
    }

}