package com.liningkang.base

import android.app.Dialog
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.*

open class BaseDialog(activity: BaseActivity, attrId: Int) : Dialog(activity, attrId),
    LifecycleEventObserver {
    constructor(activity: BaseActivity) : this(activity, R.style.base_dialog)

    companion object {
        private const val TAG = "BaseDialog"
        private const val ACTION_SHOW = 0
        private const val ACTION_DISMISS = 1
        private const val ACTION_CANCEL = 2
    }

    private val mAction: MutableLiveData<Int> = MutableLiveData()
    val activity = activity

    init {
        if (activity is LifecycleOwner) {
            mAction.observe(activity as LifecycleOwner) { action ->  // 保证调用show和dismiss不会出错
                Log.d(TAG, "BaseDialog: [activity, attrId action=$action")
                if (action === BaseDialog.ACTION_SHOW) {
                    if (!isShowing && !activity.isFinishing) {
                        Log.d(TAG, "BaseDialog: show $this")
                        super.show()
                    }
                } else if (action === BaseDialog.ACTION_DISMISS) {
                    if (isShowing) {
                        Log.d(TAG, "BaseDialog: dismiss $this")
                        super.dismiss()
                    }
                } else if (action === BaseDialog.ACTION_CANCEL) {
                    if (isShowing) {
                        Log.d(TAG, "BaseDialog: cancel $this")
                        super.cancel()
                    }
                } else {
                    throw IllegalArgumentException("BaseDialog can not do this action $action")
                }
            }
        }
        activity.lifecycle.addObserver(this) // 防止泄漏

    }

    override fun show() {
        Log.d(TAG, "show: []")
        showSafely()
    }

    override fun dismiss() {
        Log.d(TAG, "dismiss: []")
        dismissSafely()
    }

    private fun showSafely() {
        if (this.activity == null) {
            Log.d(TAG, "showSafely: []")
            return
        }
        mAction.setValue(ACTION_SHOW)
    }


    private fun dismissSafely() {
        if (activity == null) {

            Log.d(TAG, "dismissSafely: []")

            return
        }
        if (activity is LifecycleOwner
            && (activity as LifecycleOwner).lifecycle != null && (activity as LifecycleOwner).lifecycle.currentState.isAtLeast(
                Lifecycle.State.CREATED
            )
        ) {
            mAction.setValue(ACTION_DISMISS)
        } else {
            if (isShowing) {
                super.dismiss()
            }
        }
        activity.lifecycle.removeObserver(this)
    }

    override fun cancel() {
        Log.d(TAG, "cancel: []")
        cancelSafely()
    }

    private fun cancelSafely() {
        if (activity == null) {
            Log.d(TAG, "cancelSafely: return")
            return
        }
        if (activity is LifecycleOwner
            && (activity as LifecycleOwner).lifecycle != null && (activity as LifecycleOwner).lifecycle.currentState.isAtLeast(
                Lifecycle.State.CREATED
            )
        ) {
            mAction.setValue(ACTION_CANCEL)
        } else {
            if (isShowing) {
                super.cancel()
            }
        }
        activity.lifecycle.removeObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            ON_DESTROY -> {
                if (source == activity) {
                    Log.d(TAG, "onActivityDestroyed: [activity]")
                    dismissSafely()
                }
            }
            ON_CREATE -> {

            }
            ON_START -> {

            }
            ON_RESUME -> {

            }
            ON_PAUSE -> {

            }
            ON_STOP -> {

            }
            ON_ANY -> {

            }
        }
    }


}