package com.liningkang.im.activity

import android.os.Bundle
import android.view.View
import com.hyphenate.EMCallBack
import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.liningkang.base.BaseViewModelActivity
import com.liningkang.im.IMHelper
import com.liningkang.im.R
import com.liningkang.im.databinding.ActivityImBinding
import com.liningkang.utils.LogUtils

class IMActivity:BaseViewModelActivity<IMViewModel,ActivityImBinding>() {
    /**
     * 设置layoutId
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_im
    }

    /**
     * 初始化视图
     */
    override fun initView(savedInstanceState: Bundle?): Unit? {
        binding?.imActivity=this
        EMClient.getInstance().chatManager().addMessageListener(object :EMMessageListener{

            override fun onMessageReceived(messages: MutableList<EMMessage>?) {
                    LogUtils.i("收到消息:"+messages?.get(0).toString())
                    binding?.messageText?.text=messages?.get(0).toString()
            }


            override fun onCmdMessageReceived(messages: MutableList<EMMessage>?) {

            }

            /**
             * \~chinese
             * 接受到消息体的已读回执, 消息的接收方已经阅读此消息。
             */
            override fun onMessageRead(messages: MutableList<EMMessage>?) {

            }

            /**
             * \~chinese
             * 收到消息体的发送回执，消息体已经成功发送到对方设备。
             */
            override fun onMessageDelivered(messages: MutableList<EMMessage>?) {

            }

            /**
             * \~chinese
             * 收到消息体的撤回回调，消息体已经成功撤回。

             */
            override fun onMessageRecalled(messages: MutableList<EMMessage>?) {

            }

            /**
             * \~chinese
             * 接受消息发生改变的通知，包括消息ID的改变。消息是改变后的消息。
             * @param message    发生改变的消息
             * @param change
             */
            override fun onMessageChanged(message: EMMessage?, change: Any?) {
            }
        })
       return null
    }

    fun onClick(v: View){
        if (v==binding?.loginButton){
            IMHelper.login("liningkang","123456",object :EMCallBack{
                /**
                 * 程序执行成功时执行回调函数。
                 */
                override fun onSuccess() {
                   LogUtils.i("登录成功")
                }

                /**
                 * \~chinese
                 * 发生错误时调用的回调函数  @see EMError
                 * @param code           错误代码
                 * @param error          包含文本类型的错误描述。
                 */
                override fun onError(code: Int, error: String?) {
                    LogUtils.i("登录失败:${error}")
                }

                /**
                 * \~chinese
                 * @param progress       进度信息
                 * @param status         包含文件描述的进度信息, 如果SDK没有提供，结果可能是"", 或者null。
                 */
                override fun onProgress(progress: Int, status: String?) {
                    TODO("Not yet implemented")
                }
            })
        }else if (v==binding?.sendButton){
            IMHelper.sendMessage("我是liningkang,这是我发过来的消息","liningkang1")
        }
    }
}