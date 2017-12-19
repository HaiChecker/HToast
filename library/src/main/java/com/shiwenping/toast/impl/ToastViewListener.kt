package com.shiwenping.toast.impl

import android.view.View
import com.shiwenping.toast.ToastStyle

/**
 * Created by shiwenping on 17-12-19 上午11:09.
 *
 *  显示的View需要实现的接口
 *
 */
interface ToastViewListener {

    /**
     * 设置文字
     *
     * @param text  内容
     *
     */
    fun setText(text: String?)

    /**
     * 设置样式
     *
     * @param  toastStyle  显示的样式
     */
    fun setStyle(toastStyle: ToastStyle)

    /**
     * 显示
     */
    fun onShow()

    /**
     * 隐藏
     */
    fun onHide()

    /**
     * 显示的View
     *
     * @return  返回需要显示的View
     *
     */
    fun getShowView(): View
}