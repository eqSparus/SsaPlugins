package com.example.myapplication.plugin.publishers

import com.example.myapplication.plugin.events.Event

interface PluginsPublisherFunction<T : Event, R> {

    suspend fun onGetPublishResult(event: T): R

}