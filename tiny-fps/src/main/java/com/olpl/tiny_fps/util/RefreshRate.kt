package com.olpl.tiny_fps.util

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.view.WindowManager
import kotlin.math.roundToInt

internal fun getRefreshRate(context: Context): Int {
    val display = if (VERSION.SDK_INT >= VERSION_CODES.R) {
        context.display
    } else {
        @Suppress("DEPRECATION")
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    }
    return display?.refreshRate?.roundToInt() ?: 60
}