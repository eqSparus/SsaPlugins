package com.example.myapplication.function.configs

import com.example.myapplication.plugin.configs.ResultPluginConfig

class SaveFormServerResultConfig : ResultPluginConfig {
    override var pluginName: String = "SaveFormServerResultPlugin"
    override var isEnabled: Boolean = true
    override var decorators: Map<String, Boolean> = mapOf(
        "isPremium" to true,
        "isInternet" to true,
    )
}