package com.example.myapplication.consumer.decorators.factories

import com.example.myapplication.plugin.decorators.DecoratorFactory
import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin
import com.example.myapplication.consumer.decorators.PremiumAvailableDecorator

class PremiumDecoratorFactory(private val isPremium: Boolean) : DecoratorFactory {
    override fun <T : Event> createDecorator(plugin: Plugin<T>): Plugin<T> {
        return PremiumAvailableDecorator(plugin, isPremium)
    }

}