package com.example.myapplication.plugin.exceptions

class MultiException(val exceptions: List<Exception>) : RuntimeException()