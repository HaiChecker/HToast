package com.shiwenping.toast

import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.text.TextUtils
import com.shiwenping.toast.impl.ToastCallBack
import com.shiwenping.toast.impl.ToastDialogListener
import com.shiwenping.toast.impl.ToastViewListener

/**
 * Toast创建，设置，显示等待
 * Created by haichecker on 2017/12/18.
 */
class HToast {
    private var toastDialog: ToastDialogListener? = null
    private var toastView: ToastViewListener? = null
    private var handler: Handler = Handler()
    private var context: Context?
    private var callBack: ToastCallBack? = null

    constructor(context: Context?, toastStyle: ToastStyle) {
        this.context = context
        this.setStyle(toastStyle)
        toastDialog = getToastDialog()
        toastView = getToastView()
    }

    constructor(context: Context?, toastStyle: ToastStyle, callBack: ToastCallBack) {
        this.context = context
        this.setStyle(toastStyle)
        this.callBack = callBack
        toastDialog = callBack.getToastDialog()
        toastView = callBack.getToastView()
    }


    private fun getToastDialog(): ToastDialogListener {
        return ToastDialog(context)
    }

    private fun getToastView(): ToastViewListener {
        return ToastView(context)
    }

    fun setText(text: String): HToast {
        if (TextUtils.isEmpty(text)) {
            return this;
        }
        if (toastView != null) {
            toastView!!.setText(text)
        }
        return this
    }

    fun setStyle(toastStyle: ToastStyle): HToast {
        toastView!!.setStyle(toastStyle)
        return this
    }

    fun isShowing(): Boolean {
        return toastDialog!!.getDialog().isShowing
    }

    /**
     * 隐藏
     */
    fun hide() {
        hide(0)
    }

    /**
     * 隐藏，带延迟执行
     */
    fun hide(delay: Long) {
        hide(delay, null)
    }

    /**
     * 隐藏，带延迟执行，回调同志
     */
    fun hide(delay: Long, hide: DialogInterface.OnDismissListener?) {
        if (delay.compareTo(0) == 0) {
            toastView!!.onHide()
            toastDialog!!.getDialog().dismiss()
            if (hide != null) {
                hide.onDismiss(toastDialog!!.getDialog())
            }
        } else {
            handler.postDelayed({
                toastView!!.onHide()
                toastDialog!!.getDialog().dismiss()
                if (hide != null) {
                    hide.onDismiss(toastDialog!!.getDialog())
                }
            }, delay)
        }
    }

    fun show() {
        show(0, null)
    }


    fun show(delay: Long) {
        show(delay, null)
    }

    fun show(delay: Long, show: DialogInterface.OnDismissListener?) {
        if (toastView != null) {
            toastDialog!!.setView(toastView!!.getShowView())
        }
        toastView!!.onShow()
        if (delay.compareTo(0) == 0) {
            toastDialog!!.getDialog().show()
        } else {
            toastDialog!!.getDialog().show()
            handler.postDelayed({
                if (show != null) {
                    show.onDismiss(toastDialog!!.getDialog())
                }
                toastDialog!!.getDialog().dismiss()
            }, delay)
        }
    }

    companion object {
        fun create(context: Context?): HToast {
            return HToast(context, ToastStyle.TOAST_INFO)
        }

        fun create(context: Context?, toastStyle: ToastStyle): HToast {
            return HToast(context, toastStyle)
        }

        fun create(context: Context?, toastStyle: ToastStyle, callBack: ToastCallBack): HToast {
            return HToast(context, toastStyle, callBack)
        }
    }


}