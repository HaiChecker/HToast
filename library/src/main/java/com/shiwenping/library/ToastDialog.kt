package com.shiwenping.library

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import com.shiwenping.library.impl.ToastDialogListener

/**
 * 弹出的对话框
 * Created by haichecker on 2017/12/18.
 */
class ToastDialog(context: Context?) : Dialog(context), ToastDialogListener {

    private var view: View? = null

    override fun getDialog(): Dialog {
        return this
    }

    override fun setView(view: View) {
        this.view = view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_layout)
        window.setBackgroundDrawable(ColorDrawable(0))
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val layoutParams = window.attributes
        //设置背景透明度为全透明，透明度由Layout控制
        layoutParams.dimAmount = 0.0f
        //设置为居中显示
        layoutParams.gravity = Gravity.CENTER
        window.attributes = layoutParams
        setCanceledOnTouchOutside(false)

        if (view != null) {
            val rootView = findViewById<FrameLayout>(R.id.rootLayout)
            rootView.removeAllViews()
            rootView.addView(view)
        }

    }

}