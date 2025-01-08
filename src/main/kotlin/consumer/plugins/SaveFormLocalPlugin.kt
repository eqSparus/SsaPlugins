package com.example.myapplication.consumer.plugins

import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.consumer.events.SaveFormEvent
import com.example.myapplication.plugin.configs.PluginInfo

class SaveFormLocalPlugin : Plugin<SaveFormEvent> {

    override val pluginInfo: PluginInfo = object : PluginInfo {
        override var isInclude: Boolean = true
    }
    override var order: Int = 0
    override var name: String = this::class.simpleName.toString()

    override suspend fun run(event: SaveFormEvent) {
        println("Сохранено локально")
    }

    override suspend fun rollback(event: SaveFormEvent, e: Exception?) {
        println("Удалено локально")
        super.rollback(event, e)
    }
}