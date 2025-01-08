package com.example.myapplication.plugin.configs

interface ResultPluginConfig {

    var pluginName: String
    var isEnabled: Boolean
    var decorators: Map<String, Boolean>

}