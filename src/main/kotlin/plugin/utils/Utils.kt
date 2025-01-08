package com.example.myapplication.plugin.utils

import com.example.myapplication.plugin.decorators.PluginDecorator
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.plugin.plugins.ResultPlugin

fun <T : Event, R> List<ResultPlugin<T, R>>.getPlugin(): ResultPlugin<T, R> {
    val first = first()
    var head = first
    for (nextInChain in this) {
        head.next = nextInChain
        head = nextInChain
    }
    return first
}

inline fun <reified T : Event> Plugin<T>.getBasePlugin(): Plugin<T> {
    var plugin = this
    while (plugin is PluginDecorator<T>) {
        plugin = plugin.plugin
    }
    return plugin
}

fun <T : Event, R> createChain(
    first: ResultPlugin<T, R>,
    vararg plugins: ResultPlugin<T, R>,
): ResultPlugin<T, R> {
    var baseChain = first
    plugins.forEach {
        baseChain.setPluginToEnd(it)
        baseChain = it
    }
    return first
}

private fun <T : Event, R> ResultPlugin<T, R>.setPluginToEnd(plugin: ResultPlugin<T, R>) {
    var currentPlugin = this
    while (currentPlugin.next != null) {
        currentPlugin = currentPlugin.next!!
    }
    currentPlugin.next = plugin
}