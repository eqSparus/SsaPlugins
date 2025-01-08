package com.example.myapplication.consumer.configs

import com.example.myapplication.plugin.configs.PluginConfig

class SaveFormServerConfig : PluginConfig {
    override var pluginName = "SaveFormServer"
    override var isEnabled = true
    override var order = 1
    override var decorators = mapOf(
        "isPremium" to true,
        "isInternet" to true,
    )
}