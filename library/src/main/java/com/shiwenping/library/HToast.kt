package com.shiwenping.library

import android.content.Context
import android.content.DialogInterface
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater

/**
 * Toast创建，设置，显示等待
 * Created by haichecker on 2017/12/18.
 */
class HToast(context: Context?) {
    var toastDialog : ToastDialog? = null
    var tView : ToastView? = null
    var context : Context? = null
    //默认圆角大小
    var radius : Float = 0.0f
        set(value) {
            field = value
            tView!!.contentPanel!!.setCornerRadius(value)
    }

    var handler : Handler? = null
    //默认状态为INFO提示
    var style : Style = Style.TOAST_INFO
    set(value) {

        if (tView == null || tView!!.contentPanel == null){
            return
        }
        tView!!.isPay = false
        if (style == Style.TOAST_PROGRESS || value != Style.TOAST_PROGRESS)
        {
            if (tView!!.contentPanel!!.image!!.animation != null) {
                tView!!.contentPanel!!.image!!.animation.cancel()
            }
        }
        field = value
        when(value)
        {
            Style.TOAST_PROGRESS -> {
                tView!!.isPay = true
                tView!!.contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_zj_default))
                if (tView!!.contentPanel!!.image!!.animation != null) {
                    tView!!.contentPanel!!.image!!.animation.start()
                }
            }
            Style.TOAST_INFO -> {
                tView!!.contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_wz_default))
            }
            Style.TOAST_ERROR -> {
                tView!!.contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_zjsb_default))
            }
            Style.TOAST_SUCCESS ->{
                tView!!.contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_ok_default))
            }
        }
    }
    //提示的文字
    var text : String = ""
    set(value) {
        field = value
        tView!!.contentPanel!!.text!!.text = text
    }

    init {
        handler = Handler()
        this.context = context
        toastDialog = ToastDialog(context)
        tView = ToastView(context)
        toastDialog!!.setContentView(tView)
//        toastDialog!!.contentPanel!!.text!!.text = text
    }

    /**
     * 隐藏
     */
    fun hide()
    {
        hide(0)
    }

    /**
     * 隐藏，带延迟执行
     */
    fun hide(delay: Long)
    {
        hide(delay,null)
    }

    /**
     * 隐藏，带延迟执行，回调同志
     */
    fun hide(delay : Long,hide:DialogInterface.OnDismissListener?)
    {
        if (delay.compareTo(0) == 0) {
            toastDialog!!.dismiss()
            if (hide != null)
            {
                hide.onDismiss(toastDialog)
            }
        }else{
            handler!!.postDelayed(Runnable {
                toastDialog!!.dismiss()
                if (hide != null)
                {
                    hide.onDismiss(toastDialog)
                }
            },delay)
        }
    }

    fun show()
    {
        show(0,null)
    }


    fun show(delay: Long)
    {
        show(delay,null)
    }

    fun show(delay: Long,show:DialogInterface.OnShowListener?)
    {
        if (delay.compareTo(0) == 0) {
            toastDialog!!.show()
            if (show != null)
            {
                show.onShow(toastDialog)
            }
        }else{
            handler!!.postDelayed(Runnable {
                toastDialog!!.show()
                if (show != null)
                {
                    show.onShow(toastDialog)
                }
            },delay)
        }
    }

    companion object {
        fun create(context: Context?) : HToast
        {
            return HToast(context)
        }
    }



}