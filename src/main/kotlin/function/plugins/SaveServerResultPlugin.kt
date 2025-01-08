package com.example.myapplication.function.plugins

import com.example.myapplication.plugin.plugins.Result
import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.function.events.SaveFormResultEvent
import com.example.myapplication.plugin.configs.PluginInfo

class SaveServerResultPlugin : ResultPlugin<SaveFormResultEvent, String> {

    override val pluginInfo: PluginInfo = object : PluginInfo {
        override var isInclude: Boolean = true
    }
    override var name: String = this::class.simpleName.toString()
    override var next: ResultPlugin<SaveFormResultEvent, String>? = null

    override suspend fun run(event: SaveFormResultEvent): Result<String> {
        println("Сохранено на серверѣ")
        return next?.process(event) ?: Result.Success("2")
    }


}