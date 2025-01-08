package com.example.myapplication.plugin.publishers

import com.example.myapplication.plugin.events.Event

interface PluginsPublisherConsumer<T : Event> {

    suspend fun onPublishEvent(event: T)

}