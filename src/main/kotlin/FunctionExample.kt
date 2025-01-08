package com.example.myapplication

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.publishers.PluginsPublisherFunction
import com.example.myapplication.consumer.configs.AppConfigurationImpl
import com.example.myapplication.consumer.configs.SaveFormLocalConfig
import com.example.myapplication.consumer.configs.SaveFormServerConfig
import com.example.myapplication.consumer.events.Form
import com.example.myapplication.function.configs.SaveFormLocalResultConfig
import com.example.myapplication.function.configs.SaveFormServerResultConfig
import com.example.myapplication.function.events.SaveFormResultEvent
import com.example.myapplication.function.plugins.factory.InternetAvailableResultPluginFactory
import com.example.myapplication.function.plugins.factory.PremiumAvailableResultPluginFactory
import com.example.myapplication.function.plugins.factory.SaveFormLocalResultPluginFactory
import com.example.myapplication.function.plugins.factory.SaveFormServerResultPluginFactory
import com.example.myapplication.function.publishers.SaveFormChainPluginPublisherFunction
import com.example.myapplication.function.publishers.SaveFormMultiPluginPublisherFunction
import com.example.myapplication.function.publishers.SaveFormSinglePluginsPublisherFunction
import kotlinx.coroutines.runBlocking

private val config = AppConfigurationImpl(
    emptySet(),
    setOf(SaveFormServerResultConfig(), SaveFormLocalResultConfig()),
)

private val optionalPlugins = mapOf(
    "isPremium" to PremiumAvailableResultPluginFactory(),
    "isInternet" to InternetAvailableResultPluginFactory(),
)

fun main() = runBlocking {
    val saveFormEvent = SaveFormResultEvent(Form("1", "Названіе"))

    val saveFormResultServerPluginFactory =
        config.getResultConfig("SaveFormServerResultPlugin")?.let {
            SaveFormServerResultPluginFactory(it, optionalPlugins)
        }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val saveFormResultLocalPluginFactory =
        config.getResultConfig("SaveFormLocalResultPlugin")?.let {
            SaveFormLocalResultPluginFactory(it, optionalPlugins)
        }?.createPlugin() ?: throw IllegalArgumentException("Такого конфига нетъ")

    val plugins = setOf(saveFormResultLocalPluginFactory, saveFormResultServerPluginFactory)

    runResultPublisher(SaveFormSinglePluginsPublisherFunction(plugins), saveFormEvent)
    println("=======================")
    runResultPublisher(SaveFormMultiPluginPublisherFunction(plugins), saveFormEvent)
    println("=======================")
    runResultPublisher(SaveFormChainPluginPublisherFunction(plugins), saveFormEvent)
}

private suspend inline fun <reified T : Event, reified R> runResultPublisher(
    publisher: PluginsPublisherFunction<T, R>,
    event: T
) {
    try {
        val result = publisher.onGetPublishResult(event)
        println(result)
    } catch (e: Exception) {
        System.err.println(e.message)
    }
}


