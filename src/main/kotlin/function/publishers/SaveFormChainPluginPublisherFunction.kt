package com.example.myapplication.function.publishers

import com.example.myapplication.plugin.plugins.ResultPlugin
import com.example.myapplication.plugin.publishers.PluginsPublisherFunction
import com.example.myapplication.plugin.utils.createChain
import com.example.myapplication.function.events.SaveFormResultEvent

class SaveFormChainPluginPublisherFunction(
    private val plugins: Set<ResultPlugin<SaveFormResultEvent, String>>
) : PluginsPublisherFunction<SaveFormResultEvent, String> {

    override suspend fun onGetPublishResult(event: SaveFormResultEvent): String {
        val all = plugins.toMutableSet()
        val first = all.first()
        all.remove(first)
        return createChain(first, *all.toTypedArray()).process(event).getOrDefault("")
    }

}