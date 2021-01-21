package com.mobile.ninetypercent.ui.utils

import android.content.Context

object ViewUtils {
    fun dpToPx(context: Context, dpValue: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun pxToDp(context: Context, pxValue: Int): Int {
        val scale =  context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}