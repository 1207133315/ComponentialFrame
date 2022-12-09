package com.liningkang.event_dispatch

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.viewpager.widget.PagerAdapter
import com.liningkang.base.BaseCommonActivity
import kotlinx.android.synthetic.main.activity_move_conflict.*

class MoveConflictActivity : BaseCommonActivity() {
    /**
     * 设置layoutId
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.activity_move_conflict
    }

    /**
     * 初始化视图
     */
    override fun initView(savedInstanceState: Bundle?): Unit? {
        val myAdapter = MyAdapter(this)
        viewPager.adapter = myAdapter
        return null
    }

    private class MyAdapter(context: Context) : PagerAdapter() {
        private var list = arrayListOf<View>()

        init {
            list.add(View.inflate(context, R.layout.item_pager, null))
            list.add(View.inflate(context, R.layout.item_pager, null))
            list.add(View.inflate(context, R.layout.item_pager, null))
        }

        /**
         * Return the number of views available.
         */
        override fun getCount(): Int {
            return list.size
        }

        /**
         * Determines whether a page View is associated with a specific key object
         * as returned by [.instantiateItem]. This method is
         * required for a PagerAdapter to function properly.
         *
         * @param view Page View to check for association with `object`
         * @param object Object to check for association with `view`
         * @return true if `view` is associated with the key object `object`
         */
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(list.get(position))
            return list.get(position)
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

            container.removeView(list.get(position))


        }

    }
}