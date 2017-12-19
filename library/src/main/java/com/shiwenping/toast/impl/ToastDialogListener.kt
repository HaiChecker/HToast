package com.shiwenping.toast.impl

import android.app.Dialog
import android.view.View

/**
 * Created by shiwenping on 17-12-19 下午2:46.
 *
 *
 */
interface ToastDialogListener {
    /**
     * 设置需要显示的View
     *
     * @param view  显示的View
     *
     */
    fun setView(view: View)

    /**
     * 获取当前Dialog
     *
     * @return  返回的Dialog
     */
    fun getDialog() : Dialog
}