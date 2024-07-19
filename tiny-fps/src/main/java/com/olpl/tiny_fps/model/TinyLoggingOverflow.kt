package com.olpl.tiny_fps.model

sealed interface TinyLoggingOverflow {
    data object ReplaceFirst : TinyLoggingOverflow
    data object Clear : TinyLoggingOverflow
}
