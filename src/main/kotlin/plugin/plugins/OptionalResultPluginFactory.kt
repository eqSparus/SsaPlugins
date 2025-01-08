package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.events.Event

interface OptionalResultPluginFactory {

    fun <T : Event, R> createOptionalResultPlugin(plugin: ResultPlugin<T, R>): ResultPlugin<T, R>

}