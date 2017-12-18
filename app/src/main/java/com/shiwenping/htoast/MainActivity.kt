package com.shiwenping.htoast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shiwenping.library.HToast
import com.shiwenping.library.Style

class MainActivity : AppCompatActivity() {
    var toast : HToast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toast = HToast.create(this)

    }
    fun click(view : View){
        toast!!.style = Style.TOAST_PROGRESS
        toast!!.text = "Test"
        toast!!.show()
    }
}
