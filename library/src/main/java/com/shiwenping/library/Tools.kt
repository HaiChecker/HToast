package com.shiwenping.library

import android.content.Context

/**
 * 工具类
 * Created by haichecker on 2017/12/18.
 */
object Tools {
    var scale : Float = 0f;
    fun dpToPixel(dp: Float,context: Context) : Int
    {
        if (scale.compareTo(0) == 0)
        {
            scale = context.resources.displayMetrics.density
        }

        return (dp * scale).toInt()

    }
}