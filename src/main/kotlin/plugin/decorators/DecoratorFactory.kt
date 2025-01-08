package com.example.myapplication.plugin.decorators

import com.example.myapplication.plugin.events.Event
import com.example.myapplication.plugin.plugins.Plugin

interface DecoratorFactory {

    fun <T : Event> createDecorator(plugin: Plugin<T>): Plugin<T>

}