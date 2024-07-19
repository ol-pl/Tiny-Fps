package com.olpl.tiny_fps.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TinyLogging(
    val maxCount: Int = 15,
    val onOverflow: TinyLoggingOverflow = TinyLoggingOverflow.Clear,
    val autoLogOnBadFps: Boolean = true,
    val textColor: Color = Color.Gray,
    val fontSize: TextUnit = 20.sp,
) {
    internal val messages: SnapshotStateList<String> = mutableStateListOf()

    internal fun clearLogs() {
        messages.clear()
    }

    internal fun pushLog(key: String?, value: Any) {
        when (onOverflow) {
            TinyLoggingOverflow.Clear -> {
                if (messages.size == maxCount) {
                    messages.clear()
                }
                messages.add("${if (key != null) "$key -> " else ""}$value")
            }

            TinyLoggingOverflow.ReplaceFirst -> {
                if (messages.size == maxCount) {
                    messages.removeLastOrNull()
                    messages.add(0, "${if (key != null) "$key -> " else ""}$value")
                } else {
                    messages.add("${if (key != null) "$key -> " else ""}$value")
                }
            }
        }
    }
}
