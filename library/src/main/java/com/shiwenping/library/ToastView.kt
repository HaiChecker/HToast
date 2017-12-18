package com.shiwenping.library

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout

/**
 * Created by haichecker on 2017/12/18.
 */
class ToastView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) {
    var contentPanel : ContentLayout? = null
    var isPay : Boolean = false
    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context?) : this(context,null,0){
        var view = LayoutInflater.from(getContext()).inflate(R.layout.toast_layout,this,false)
        contentPanel = view.findViewById(R.id.contentPanel)
        contentPanel!!.isPay = isPay
        addView(view,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
    }
}