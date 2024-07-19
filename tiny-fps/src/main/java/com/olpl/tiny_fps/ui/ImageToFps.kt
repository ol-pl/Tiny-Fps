package com.olpl.tiny_fps.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.olpl.tiny_fps.TinyFps

@NonRestartableComposable
@Composable
internal fun ImageToFps(
    currentFps: Int,
) {
    val image = remember(key1 = currentFps) {
        when (currentFps) {
            in 0..<TinyFps.fpsGrade.low -> TinyFps.uiParams.images.lowFps
            in TinyFps.fpsGrade.low..<TinyFps.fpsGrade.medium -> TinyFps.uiParams.images.mediumFps
            in TinyFps.fpsGrade.medium..TinyFps.fpsGrade.high -> TinyFps.uiParams.images.highFps
            else -> TinyFps.uiParams.images.highFps
        }
    }
    AsyncImage(
        model = image,
        contentDescription = "image to current visible fps",
        modifier = Modifier.size(TinyFps.uiParams.imageSize),
        contentScale = ContentScale.Fit
    )
}