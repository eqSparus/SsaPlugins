package com.example.myapplication.function.plugins

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Result
import com.example.myapplication.plugin.plugins.ResultPlugin

class InternetAvailableResultPlugin<T : Event, R>(
    override var next: ResultPlugin<T, R>?,
    private val isInternet: Boolean,
) : ResultPlugin<T, R> {

    override var pluginInfo: PluginInfo = next?.pluginInfo
        ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
    override var name: String =
        next?.name ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")

    override suspend fun run(event: T): Result<R> = if (isInternet) {
        println("Проверка на интернетъ")
        next?.process(event)
            ?: throw IllegalArgumentException("Слѣдующій плагинъ долженъ быть обязателенъ")
    } else {
        throw IllegalStateException("Интернета нетъ")
    }


}