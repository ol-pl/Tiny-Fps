package com.olpl.tiny_fps.model

import androidx.compose.runtime.Immutable
import kotlin.math.roundToInt

@Immutable
data class FpsGrade(
    val low: Int = 60 / 2,
    val medium: Int = (60 / 1.5).roundToInt(),
    val high: Int = 60,
)
