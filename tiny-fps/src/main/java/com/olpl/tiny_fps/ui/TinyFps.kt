package com.olpl.tiny_fps.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.olpl.tiny_fps.TinyFps

@NonRestartableComposable
@Composable
internal fun TinyFps() {
    val currentFps = TinyFps.currentFps
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.5f)
                .align(TinyFps.uiParams.alignment)
        ) {
            ContentContainer {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .width(IntrinsicSize.Max)
                        .padding(horizontal = 5.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onLongPress = {
                                TinyFps.reset()
                            })
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    ImageToFps(currentFps = currentFps)
                    FpsViewText(currentFps = currentFps)
                }
                if (TinyFps.logging.messages.isNotEmpty()) {
                    LogsView()
                }
            }
        }
    }
}

@NonRestartableComposable
@Composable
private fun ContentContainer(
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(TransparentRed),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        content()
    }
}
