package com.example.myapplication.function.configs

import com.example.myapplication.plugin.configs.ResultPluginConfig

class SaveFormLocalResultConfig : ResultPluginConfig {
    override var pluginName: String = "SaveFormLocalResultPlugin"
    override var isEnabled: Boolean = true
    override var decorators: Map<String, Boolean> = mapOf(
        "isPremium" to false,
        "isInternet" to false,
    )
}