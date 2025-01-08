package com.example.myapplication.consumer.decorators.factories

import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.consumer.decorators.InternetAvailableDecorator

class InternetDecoratorFactory(private val isInternet: Boolean) : DecoratorFactory {
    override fun <T : Event> createDecorator(plugin: Plugin<T>): Plugin<T> {
        return InternetAvailableDecorator(plugin, isInternet)
    }
}