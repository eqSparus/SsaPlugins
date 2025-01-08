package com.example.myapplication.plugin.decorators

import com.example.myapplication.plugin.configs.PluginInfo
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin

interface PluginDecorator<T : Event> : Plugin<T> {
    val plugin: Plugin<T>
    override var order: Int
        get() = plugin.order
        set(value) {}
    override var name: String
        get() = plugin.name
        set(value) {}
    override val pluginInfo: PluginInfo
        get() = plugin.pluginInfo
}