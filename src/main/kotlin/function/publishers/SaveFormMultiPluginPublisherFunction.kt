package com.example.myapplication.function.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.PluginsPublisherFunction
import com.example.myapplication.function.events.SaveFormResultEvent

class SaveFormMultiPluginPublisherFunction(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : PluginsPublisherFunction<SaveFormResultEvent, List<String>> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): List<String> {
        val result = mutableListOf<String?>()
        plugins.forEach {
            try {
                result.add(it.process(event).getOrNull())
            } catch (e: Exception) {
                System.err.println()
            }
        }
        return result.filterNotNull()
    }

}
