package com.example.myapplication.consumer.decorators

import com.example.myapplication.plugin.decorators.PluginDecorator
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin

class PremiumAvailableDecorator<T : Event>(
    override val plugin: Plugin<T>,
    private val isPremium: Boolean,
) : PluginDecorator<T> {

    override suspend fun run(event: T) {
        if (isPremium) {
            println("Преміумъ есть")
            plugin.process(event)
        } else {
            throw IllegalStateException("Преміума нетъ")
        }
    }
}