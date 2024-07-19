package com.olpl.tiny_fps

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import com.olpl.tiny_fps.model.FpsCounter
import com.olpl.tiny_fps.model.FpsGrade
import com.olpl.tiny_fps.model.TinyLogging
import com.olpl.tiny_fps.model.TinyUiParams
import com.olpl.tiny_fps.ui.TinyFps
import com.olpl.tiny_fps.util.getRefreshRate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

object TinyFps {
    private var fpsCounter: FpsCounter? = null
    private var scope = CoroutineScope(Dispatchers.Main)

    var currentFps by mutableIntStateOf(0)
        private set

    private var availableRefreshRate by mutableIntStateOf(60)

    internal val fpsGrade by derivedStateOf {
        FpsGrade(
            low = availableRefreshRate / 2,
            medium = (availableRefreshRate / 1.5).roundToInt(),
            high = availableRefreshRate
        )
    }

    internal var logging = TinyLogging()
        private set

    internal var uiParams = TinyUiParams()
        private set

    fun init(context: Context): TinyFps {
        this.scope = CoroutineScope(Dispatchers.Main.immediate)

        availableRefreshRate = getRefreshRate(context)
        fpsCounter = FpsCounter(context, this.scope).also {
            it.fpsFlow.withIndex().onEach { (_, value) ->
                currentFps = value
            }.launchIn(scope)
        }

        setContentView(context)

        return this
    }

    private fun setContentView(context: Context) {
        (context as Activity).window.decorView
            .findViewById<ViewGroup>(android.R.id.content)
            .addView(ComposeView(context).apply {
                setContent {
                    TinyFps()
                }
            })
    }

    fun reset() {
        logging.clearLogs()
        fpsCounter?.reset()
    }

    fun setLogging(logging: TinyLogging = TinyLogging()) {
        this.logging = logging
    }

    fun launchAutoLogging() {
        if (logging.autoLogOnBadFps) {
            scope.launch {
                fpsCounter?.fpsFlow?.first {
                    it >= availableRefreshRate * 0.8
                }
                fpsCounter?.fpsFlow?.onEach { fps ->
                    if (fps <= availableRefreshRate / 2) {
                        pushLog("Low FPS", fps)
                    }
                }?.launchIn(scope)
            }
        }
    }

    fun setUiParams(uiParams: TinyUiParams) {
        this.uiParams = uiParams
    }

    fun destroy(context: Context) {
        (context as Activity).window.decorView
            .findViewById<ViewGroup>(android.R.id.content)
            .removeViewAt(1)
        this.scope.cancel()
    }

    fun pushLog(key: String? = null, value: Any) {
        logging.pushLog(key, value)
    }

}
