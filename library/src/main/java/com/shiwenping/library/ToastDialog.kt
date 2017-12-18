package com.shiwenping.library

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout

/**
 * 弹出的对话框
 * Created by haichecker on 2017/12/18.
 */
class ToastDialog(context: Context?) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(0))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        var layoutParams = window.attributes
        //设置背景透明度为全透明，透明度由Layout控制
        layoutParams.dimAmount = 0.0f
        //设置为居中显示
        layoutParams.gravity = Gravity.CENTER
        window.attributes = layoutParams
        setCanceledOnTouchOutside(false)
    }

    override fun setContentView(view: View?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.setContentView(view)
    }
}