package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event

interface Plugin<T : Event> {

    val pluginInfo: PluginInfo
    var order: Int
    var name: String

    suspend fun run(event: T)

    suspend fun process(event: T) {
        try {
            run(event)
        } catch (e: Exception) {
            rollback(event, e)
        }
    }

    suspend fun rollback(event: T, e: Exception? = null) {
        e?.let { throw e }
    }
}