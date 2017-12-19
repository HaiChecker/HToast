package com.shiwenping.library

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.FrameLayout
import com.shiwenping.library.impl.ToastViewListener

/**
 * ToastView
 * Created by haichecker on 2017/12/18.
 */
class ToastView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attrs, defStyleAttr) , ToastViewListener{

    var contentPanel : ContentLayout? = null
    constructor(context: Context?,attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context?) : this(context,null,0){
        val view = LayoutInflater.from(getContext()).inflate(R.layout.toast_layout,this,false)
        contentPanel = view.findViewById(R.id.contentPanel)
        addView(view,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
    }


    var textStr : String? = null

    var toastStyleInfo: ToastStyle = ToastStyle.TOAST_INFO

    var animationInfo : Animation? = null

    override fun setText(text: String?) {
        this.textStr = text
        if (contentPanel != null)
        {
            contentPanel!!.text!!.text = textStr
        }
    }

    override fun setStyle(toastStyle: ToastStyle) {
        toastStyleInfo = toastStyle
        if (contentPanel != null)
        {
            if (toastStyle != ToastStyle.TOAST_PROGRESS)
            {
                contentPanel!!.image!!.clearAnimation()
            }else{
                animationInfo = createAnim()
            }

            when(toastStyle)
            {
                ToastStyle.TOAST_INFO -> {
                    contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_wz_default))
                }
                ToastStyle.TOAST_ERROR -> {
                    contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_zjsb_default))
                }
                ToastStyle.TOAST_SUCCESS ->{
                    contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_ok_default))
                }
                ToastStyle.TOAST_PROGRESS ->{
                    contentPanel!!.image!!.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.msg_icon_zj_default))
                    contentPanel!!.image!!.startAnimation(animationInfo)
                }
            }
        }
    }

    fun createAnim() : Animation{
        val anim = RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 2000
        anim.repeatCount = Animation.INFINITE
        anim.fillAfter = true
        anim.interpolator = DecelerateInterpolator()
        return anim
    }

    override fun onShow() {
        setStyle(toastStyleInfo)
        setText(textStr)

    }

    override fun onHide() {
        if (contentPanel != null)
        {
            contentPanel!!.image!!.clearAnimation()
        }
    }

    override fun getShowView(): View {
        return this
    }
}