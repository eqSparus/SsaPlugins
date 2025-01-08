package com.example.myapplication.plugin.configs

interface AppPluginConfiguration {

    fun getConfig(pluginName: String): PluginConfig?

    fun getResultConfig(pluginName: String): ResultPluginConfig?
}