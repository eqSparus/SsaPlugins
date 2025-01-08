package com.example.myapplication.consumer.publishers

import com.example.myapplication.consumer.events.SaveFormEvent
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.publishers.PluginsPublisherConsumer

class SaveFormPluginsSinglePublisherConsumer(private val plugins: Set<Plugin<SaveFormEvent>>) :
    PluginsPublisherConsumer<SaveFormEvent> {

    override suspend fun onPublishEvent(event: SaveFormEvent) {
        plugins.filter { it.pluginInfo.isInclude }.find { it.name == event.type.title }?.process(event)
    }

}

