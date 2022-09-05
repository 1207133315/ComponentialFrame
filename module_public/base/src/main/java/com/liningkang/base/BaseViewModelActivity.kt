package com.liningkang.base

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import java.lang.reflect.ParameterizedType

/**
 * @description ViewModel+DataBinding的Activity基类
 */
abstract class BaseViewModelActivity<VM : BaseViewModel<*>, VDB : ViewDataBinding> :
    BaseActivity() {
    var mLoadDialog // 加载框
            : Dialog? = null


    var viewModel: VM? = null
    var binding: VDB? = null

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //打印堆栈ID
//        LogUtils.e("getTaskId = $taskId")

        viewModel = ViewModelProvider(this).get(getTClass()!!)
        //        binding.setLifecycleOwner(this);
        if (viewModel != null) {
            lifecycle.addObserver(viewModel!!)
        }
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        //所有布局中dababinding对象变量名称都是vm
        binding?.executePendingBindings() //立即更新UI

        initView(savedInstanceState)


    }


    /**
     * 获取泛型对相应的Class对象
     * @return
     */
    private fun getTClass(): Class<VM?>? {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        val type: ParameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        //返回表示此类型实际类型参数的 Type 对象的数组()，想要获取第二个泛型的Class，所以索引写1
        return type.actualTypeArguments[0] as Class<VM?>? //<T>
    }


    /**
     * 得到图片的路径
     *
     * @param fileName
     * @param requestCode
     * @param data
     * @return
     */
//    fun getFilePath(fileName: String?, requestCode: Int, data: Intent): String? {
//        if (requestCode == CAMERA) {
//            return fileName
//        } else if (requestCode == PHOTO) {
//            val uri: Uri? = data.data
//            return FileUtils.getFilePathByUri(this, uri)
//        }
//        return null
//    }
}