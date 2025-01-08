package com.example.myapplication.function.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.PluginsPublisherFunction
import com.example.myapplication.function.events.SaveFormResultEvent

class SaveFormSinglePluginsPublisherFunction(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : PluginsPublisherFunction<SaveFormResultEvent, String> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): String =
        plugins.find { it.name == event.type.title }?.process(event)?.getOrNull()
            ?: throw IllegalArgumentException("Плагнъ не найденъ")
}