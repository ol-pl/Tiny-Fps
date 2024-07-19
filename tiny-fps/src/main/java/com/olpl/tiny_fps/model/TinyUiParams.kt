package com.olpl.tiny_fps.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class TinyUiParams(
    val alignment: Alignment = Alignment.TopStart,
    val fontSize: TextUnit = 24.sp,
    val textColor: Color = Color.White,
    val images: ImageToFps = ImageToFps(),
    val imageSize: Dp = 32.dp,
)
