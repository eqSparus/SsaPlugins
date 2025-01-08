package com.example.myapplication.plugin.publishers

interface PluginPublisherSupplier<R> {

    suspend fun get(): R

}