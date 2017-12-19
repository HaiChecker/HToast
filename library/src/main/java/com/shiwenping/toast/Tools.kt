package com.shiwenping.toast

import android.content.Context
import android.util.TypedValue
import android.view.View

/**
 * 工具类
 * Created by haichecker on 2017/12/18.
 */
object Tools {
    var scale: Float = 0f;
    fun dpToPixel(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()

    }
}