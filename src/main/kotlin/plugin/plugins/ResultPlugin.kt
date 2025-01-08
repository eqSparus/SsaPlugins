package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event

interface ResultPlugin<T : Event, R> {

    val pluginInfo: PluginInfo
    var name: String
    var next: ResultPlugin<T, R>?

    suspend fun run(event: T): Result<R>

    suspend fun process(event: T): Result<R> = run(event).onFail {
        rollback(event, it)
    }

    suspend fun rollback(event: T, e: Result<R>): Result<R> = e

}
