package com.olpl.tiny_fps.ui

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.olpl.tiny_fps.TinyFps

@NonRestartableComposable
@Composable
internal fun FpsViewText(
    currentFps: Int,
) {
    BasicText(
        text = "FPS: $currentFps",
        modifier = Modifier
            .wrapContentWidth()
            .height(IntrinsicSize.Max),
        style = TextStyle(
            fontSize = TinyFps.uiParams.fontSize,
            color = TinyFps.uiParams.textColor,
            textAlign = TextAlign.Start
        )
    )
}
