package com.example.myapplication.plugin.utils

sealed interface PluginResult<R> {

    data class Success<R>(val r: R) : PluginResult<R>

    data class Failed<R>(val e: Throwable) : PluginResult<R>

    fun getOrNull(): R? = (this as? Success<R>)?.r

    fun getOrDefault(defaultValue: R): R = (this as? Success<R>)?.r ?: defaultValue

    suspend fun onFail(block: suspend (Throwable) -> PluginResult<R>): PluginResult<R> =
        if (this is Success) {
            this
        } else {
            block.invoke((this as Failed).e)
        }

    suspend fun onSuccess(block: suspend (R) -> PluginResult<R>): PluginResult<R> =
        if (this !is Success) {
            block.invoke((this as Success).r)
        } else {
            this
        }


}
