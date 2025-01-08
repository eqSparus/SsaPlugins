package com.example.myapplication.consumer.publishers

import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.publishers.PluginsPublisherConsumer
import com.example.myapplication.consumer.events.SaveFormEvent

class SaveFormPluginsMultiPublisherConsumer(private val plugins: Set<Plugin<SaveFormEvent>>) :
    PluginsPublisherConsumer<SaveFormEvent> {

    override suspend fun onPublishEvent(event: SaveFormEvent) {
        plugins.filter { it.pluginInfo.isInclude }.sortedBy { it.order }.forEach { it.process(event) }
    }
}