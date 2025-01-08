package com.example.myapplication

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.publishers.PluginsPublisherConsumer
import com.example.myapplication.consumer.configs.AppConfigurationImpl
import com.example.myapplication.consumer.configs.SaveFormLocalConfig
import com.example.myapplication.consumer.configs.SaveFormServerConfig
import com.example.myapplication.consumer.decorators.factories.InternetDecoratorFactory
import com.example.myapplication.consumer.decorators.factories.PremiumDecoratorFactory
import com.example.myapplication.consumer.events.Form
import com.example.myapplication.consumer.events.SaveFormEvent
import com.example.myapplication.consumer.plugins.factories.SaveFormLocalPluginFactory
import com.example.myapplication.consumer.plugins.factories.SaveFormServerPluginFactory
import com.example.myapplication.consumer.publishers.SaveFormPluginsMultiPublisherConsumer
import com.example.myapplication.consumer.publishers.SaveFormPluginsSinglePublisherConsumer
import kotlinx.coroutines.runBlocking

private val decoratorsFactory = mapOf(
    "isInternet" to InternetDecoratorFactory(true),
    "isPremium" to PremiumDecoratorFactory(true),
)
private val config = AppConfigurationImpl(
    setOf(SaveFormLocalConfig(), SaveFormServerConfig()),
    emptySet()
)

fun main() = runBlocking {
    val serverPlugin = config.getConfig("SaveFormServer")?.let {
        SaveFormServerPluginFactory(it, decoratorsFactory)
    }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val localPlugin = config.getConfig("SaveFormLocal")?.let {
        SaveFormLocalPluginFactory(it, decoratorsFactory)
    }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val plugins = setOf(serverPlugin, localPlugin)

    val event = SaveFormEvent(Form("1", "form"))

    runPublisher(SaveFormPluginsSinglePublisherConsumer(plugins), event)
    println("====================")
    runPublisher(SaveFormPluginsMultiPublisherConsumer(plugins), event)
}

private suspend fun <T : Event> runPublisher(publisher: PluginsPublisherConsumer<T>, event: T) {
    try {
        publisher.onPublishEvent(event)
    } catch (e: Exception) {
        System.err.println(e.message)
    }
}