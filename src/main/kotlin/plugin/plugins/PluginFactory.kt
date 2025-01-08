package com.example.myapplication.plugin.plugins

import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.events.Event

interface PluginFactory<T : Event> {

    val basePlugin: Plugin<T>
    val config: PluginConfig
    val decorators: Map<String, DecoratorFactory>

    fun createPlugin(): Plugin<T> {
        var basePlugin = basePlugin
        basePlugin.order = config.order
        basePlugin.name = config.pluginName
        basePlugin.pluginInfo.isInclude = config.isEnabled

        config.decorators.filterValues { it }.keys
            .mapNotNull { decorators[it] }
            .forEach { basePlugin = it.createDecorator(basePlugin) }
        return basePlugin
    }

}