package com.example.myapplication.consumer.decorators

import com.example.myapplication.plugin.decorators.PluginDecorator
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin

class InternetAvailableDecorator<T : Event>(
    override val plugin: Plugin<T>,
    private val isInternet: Boolean,
) : PluginDecorator<T> {

    override suspend fun run(event: T) {
        if (isInternet) {
            println("Интернетъ есть")
            plugin.process(event)
        } else {
            throw IllegalStateException("Интернета нетъ")
        }
    }
}