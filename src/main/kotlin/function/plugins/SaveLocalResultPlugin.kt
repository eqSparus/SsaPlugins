package com.example.myapplication.function.plugins

import com.example.myapplication.plugin.plugins.Result
import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.function.events.SaveFormResultEvent
import com.example.myapplication.plugin.configs.PluginInfo


class SaveLocalResultPlugin : ResultPlugin<SaveFormResultEvent, String> {

    override val pluginInfo: PluginInfo = object :PluginInfo {
        override var isInclude: Boolean = true
    }
    override var name: String = this::class.simpleName.toString()

    override var next: ResultPlugin<SaveFormResultEvent, String>? = null

    override suspend fun run(event: SaveFormResultEvent): Result<String> {
        println("Сохранено локально")
        return next?.process(event) ?: Result.Success("1")
    }

    override suspend fun rollback(event: SaveFormResultEvent, e: Result<String>): Result<String> {
        println("Откатъ локально")
        return Result.Success(event.form.id)
    }

}