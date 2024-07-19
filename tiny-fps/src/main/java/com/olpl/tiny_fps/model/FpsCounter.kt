package com.olpl.tiny_fps.model

import android.content.Context
import android.os.SystemClock
import com.olpl.tiny_fps.util.getRefreshRate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

internal class FpsCounter(
    context: Context,
    scope: CoroutineScope,
) {
    private var coroutineScope = scope

    private val _fpsFlow = MutableSharedFlow<Int>()
    val fpsFlow = _fpsFlow.asSharedFlow()

    private val refreshRate = getRefreshRate(context)
    private val frameInterval = (1000 / refreshRate).toLong()
    private val frameTimes = ArrayDeque<Long>()

    init {
        reset()
        launchFpsCounter()
    }

    fun reset() {
        frameTimes.clear()
    }

    @Synchronized
    private fun frameRendered() {
        val currentTime = SystemClock.elapsedRealtime()
        frameTimes.addLast(currentTime)

        if (frameTimes.size > refreshRate) {
            frameTimes.removeFirst()
        }
    }

    private fun launchFpsCounter() {
        coroutineScope.launch {

            while (true) {
                val currentTime = SystemClock.elapsedRealtime()

                if (frameTimes.size > 1) {
                    val firstFrameTime = frameTimes.first()
                    val lastFrameTime = frameTimes.last()
                    val frameInterval =
                        (lastFrameTime - firstFrameTime).toFloat() / (frameTimes.size - 1)
                    val fps = 1000f / frameInterval
                    _fpsFlow.emit(fps.roundToInt())
                }

                frameRendered()

                val sleepTime = frameInterval - (SystemClock.elapsedRealtime() - currentTime)

                if (sleepTime > 0) {
                    delay(sleepTime)
                }
            }
        }
    }
}