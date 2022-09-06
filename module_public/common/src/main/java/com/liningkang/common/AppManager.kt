package com.liningkang.common

import android.app.Activity
import android.os.Process
import kotlin.jvm.Volatile
import com.liningkang.common.AppManager
import java.lang.Exception
import java.util.*

object  AppManager {
    /**
     * 维护activity的栈结构
     */
    private var mActivityStack: Stack<Activity?>? = null

    /**
     * 添加Activity到堆栈
     *
     * @param activity activity实例
     */
    fun addActivity(activity: Activity?) {
        if (mActivityStack == null) {
            mActivityStack = Stack()
        }
        mActivityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（栈中最后一个压入的）
     *
     * @return 当前（栈顶）activity
     */
    fun currentActivity(): Activity? {
        return if (mActivityStack != null && !mActivityStack!!.isEmpty()) {
            mActivityStack!!.lastElement()
        } else null
    }

    /**
     * 结束除当前activtiy以外的所有activity
     * 注意：不能使用foreach遍历并发删除，会抛出java.util.ConcurrentModificationException的异常
     *
     * @param activity 不需要结束的activity
     */
    fun finishOtherActivity(activity: Activity) {
        if (mActivityStack != null) {
            val it: Iterator<Activity?> = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val temp = it.next()
                if (temp != null && temp !== activity) {
                    finishActivity(temp)
                }
            }
        }
    }

    /**
     * 结束除这一类activtiy以外的所有activity
     * 注意：不能使用foreach遍历并发删除，会抛出java.util.ConcurrentModificationException的异常
     *
     * @param cls 不需要结束的activity
     */
    fun finishOtherActivity(cls: Class<*>) {
        if (mActivityStack != null) {
            val it: Iterator<Activity?> = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activity = it.next()
                if (activity!!.javaClass != cls) {
                    finishActivity(activity)
                }
            }
        }
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        if (mActivityStack != null && !mActivityStack!!.isEmpty()) {
            val activity = mActivityStack!!.lastElement()
            finishActivity(activity)
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity 指定的activity实例
     */
     fun finishActivity(activity: Activity?) {
        if (activity != null) {
            if (mActivityStack != null && mActivityStack!!.contains(activity)) { // 兼容未使用AppManager管理的实例
                mActivityStack!!.remove(activity)
            }
            activity.finish()
        }
    }

    /**
     * 结束指定类名的所有Activity
     *
     * @param cls 指定的类的class
     */
    fun finishActivity(cls: Class<*>) {
        if (mActivityStack != null) {
            val it: Iterator<Activity?> = mActivityStack!!.iterator()
            while (it.hasNext()) {
                val activity = it.next()
                if (activity!!.javaClass == cls) {
                    finishActivity(activity)
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        if (mActivityStack != null) {
            var i = 0
            val size = mActivityStack!!.size
            while (i < size) {
                if (null != mActivityStack!![i]) {
                    mActivityStack!![i]!!.finish()
                }
                i++
            }
            mActivityStack!!.clear()
        }
    }

    fun removeActivity(activity: Activity?){
        if (activity != null) {
            if (mActivityStack != null && mActivityStack!!.contains(activity)) { // 兼容未使用AppManager管理的实例
                mActivityStack!!.remove(activity)
            }
        }
    }
    /**
     * 退出应用程序
     */
    fun exitApp() {
        try {
            finishAllActivity()
            // 退出JVM(java虚拟机),释放所占内存资源,0表示正常退出(非0的都为异常退出)
            System.exit(0)
            // 从操作系统中结束掉当前程序的进程
            Process.killProcess(Process.myPid())
        } catch (e: Exception) {
        }
    }


}