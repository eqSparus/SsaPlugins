package com.example.myapplication.plugin.configs

interface PluginConfig {

    var pluginName: String
    var isEnabled: Boolean
    var order: Int
    var decorators: Map<String, Boolean>

}