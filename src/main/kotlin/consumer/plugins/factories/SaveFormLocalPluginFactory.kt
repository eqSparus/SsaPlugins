package com.example.myapplication.consumer.plugins.factories

import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.plugins.PluginFactory
import com.example.myapplication.consumer.events.SaveFormEvent
import com.example.myapplication.consumer.plugins.SaveFormLocalPlugin

class SaveFormLocalPluginFactory(
    override val config: PluginConfig,
    override val decorators: Map<String, DecoratorFactory>
) : PluginFactory<SaveFormEvent> {

    override val basePlugin: Plugin<SaveFormEvent> = SaveFormLocalPlugin()

}