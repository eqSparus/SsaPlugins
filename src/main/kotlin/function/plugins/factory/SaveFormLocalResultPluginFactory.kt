package com.example.myapplication.function.plugins.factory

import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.plugin.plugins.OptionalResultPluginFactory
import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.plugins.ResultPluginFactory
import com.example.myapplication.function.events.SaveFormResultEvent
import com.example.myapplication.function.plugins.SaveLocalResultPlugin

class SaveFormLocalResultPluginFactory(
    override val config: ResultPluginConfig,
    override val decorators: Map<String, OptionalResultPluginFactory>,
) : ResultPluginFactory<SaveFormResultEvent, String> {

    override val basePlugin: ResultPlugin<SaveFormResultEvent, String> = SaveLocalResultPlugin()

}