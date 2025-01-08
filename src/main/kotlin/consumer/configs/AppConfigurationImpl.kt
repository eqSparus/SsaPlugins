package com.example.myapplication.consumer.configs

import com.example.myapplication.plugin.configs.AppPluginConfiguration
import com.example.myapplication.plugin.configs.PluginConfig
import com.example.myapplication.plugin.configs.ResultPluginConfig
import com.example.myapplication.function.configs.SaveFormLocalResultConfig
import com.example.myapplication.function.configs.SaveFormServerResultConfig

class AppConfigurationImpl(
    private val configConsumer: Set<PluginConfig>,
    private val configFunction: Set<ResultPluginConfig>,
) : AppPluginConfiguration {

    override fun getConfig(pluginName: String): PluginConfig? =
        configConsumer.find { it.pluginName == pluginName }

    override fun getResultConfig(pluginName: String): ResultPluginConfig? =
        configFunction.find { it.pluginName == pluginName }
}