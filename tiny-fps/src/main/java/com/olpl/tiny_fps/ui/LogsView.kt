package com.olpl.tiny_fps.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.olpl.tiny_fps.TinyFps

@Composable
internal fun LogsView() {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentSize()
    ) {
        items(items = TinyFps.logging.messages) {
            BasicText(
                text = it,
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
    }
}