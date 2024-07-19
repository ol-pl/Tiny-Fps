package com.olpl.tiny_fps.model

import androidx.compose.runtime.Immutable
import com.olpl.tiny_fps.R

@Immutable
data class ImageToFps(
    val lowFps: Int = R.drawable.bad,
    val mediumFps: Int = R.drawable.norm,
    val highFps: Int = R.drawable.fine,
)
