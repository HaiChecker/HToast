package com.shiwenping.htoast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.shiwenping.library.HToast
import com.shiwenping.library.ToastStyle

class MainActivity : AppCompatActivity() {
    var toast : HToast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = HToast.create(this)

    }
    fun click(view : View){
        toast!!.setText("加载中")
                .setStyle(ToastStyle.TOAST_PROGRESS)
                .show()
        Handler().postDelayed(Runnable {
                    toast!!.setText("加载数据中...")
            Handler().postDelayed(Runnable {
                    toast!!.setStyle(ToastStyle.TOAST_ERROR)
                            .setText("加载失败")
                            .hide(1000)
            },1000)
                },5000)
    }
}
