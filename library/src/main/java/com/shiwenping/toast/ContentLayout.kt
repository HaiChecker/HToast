package com.shiwenping.toast

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * 内容布局，可实现动态圆角
 * Created by haichecker on 2017/12/18.
 */
class ContentLayout(context: Context?,attributeSet: AttributeSet?,def:Int) : LinearLayout(context,attributeSet,def) {

    var mCornerRadius : Float = 0f

    var mBackgroundColor : Int = 0

    var image : ImageView? = null

    var text : TextView? = null

    var animator = animator()

    var isPay : Boolean = false

    constructor(context: Context?) : this(context,null,0)
    {
        init(context,null)
    }

    constructor(context: Context?,attributeSet: AttributeSet?) : this(context,attributeSet,0) {
        init(context, attributeSet)
    }

    private fun animator() : RotateAnimation{
        val anim  = RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_PARENT,0.5f)
        anim.fillAfter = true
        anim.repeatMode = Animation.RESTART
        anim.interpolator = AccelerateInterpolator()
        return anim
    }

    private fun init(context: Context?, attributeSet: AttributeSet?) {
        val typeArray = context!!.obtainStyledAttributes(attributeSet, R.styleable.ContentLayout);
        mBackgroundColor = typeArray.getColor(R.styleable.ContentLayout_default_color, ContextCompat.getColor(context, R.color.default_color))
        mCornerRadius = typeArray.getDimension(R.styleable.ContentLayout_radius, 16f)
        typeArray.recycle()
        initBackground(mBackgroundColor,mCornerRadius)
    }

    private fun initBackground(color : Int,cornerRadius : Float)
    {
        var drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.setColor(color)
        drawable.cornerRadius = cornerRadius;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            background = drawable
        }else{
            setBackgroundDrawable(drawable)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        text = findViewById(R.id.text)
        image = findViewById(R.id.img)
        image!!.animation = animator
        if (isPay)
        {
            image!!.animation.start()
        }
    }

    fun setCornerRadius(radius: Float)
    {
        this.mCornerRadius = radius;
        initBackground(mBackgroundColor,mCornerRadius)
    }

    fun setBaseColor(color: Int)
    {
        this.mBackgroundColor = color
        initBackground(mBackgroundColor,mCornerRadius)
    }

}