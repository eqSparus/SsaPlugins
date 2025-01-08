package com.example.myapplication.function.plugins

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.utils.PluginResult
import com.example.myapplication.plugin.plugins.ResultPlugin
import java.lang.IllegalStateException

class PremiumAvailableResultPlugin<T : Event, R>(
    override var next: ResultPlugin<T, R>?,
    private val isPremium: Boolean,
) : ResultPlugin<T, R> {

    override val pluginInfo: PluginInfo = next?.pluginInfo
        ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
    override var name: String =
        next?.name ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")

    override suspend fun run(event: T): PluginResult<R> = if (isPremium) {
        println("Проверка на премиумъ")
        next?.process(event)
            ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
    } else {
        throw IllegalStateException("Премиума нетъ")
    }

}