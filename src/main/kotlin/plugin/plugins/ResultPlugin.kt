package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.utils.PluginResult

interface ResultPlugin<T : Event, R> {

    val pluginInfo: PluginInfo
    var name: String
    var next: ResultPlugin<T, R>?

    suspend fun run(event: T): PluginResult<R>

    suspend fun process(event: T): PluginResult<R> = try {
        run(event).onFail {
            rollback(event, it)
        }
    } catch (e: Throwable) {
        PluginResult.Failed(e)
    }

    suspend fun rollback(event: T, e: Throwable): PluginResult<R> = PluginResult.Failed(e)

}
