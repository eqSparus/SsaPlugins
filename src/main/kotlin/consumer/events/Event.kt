package com.example.myapplication.consumer.events

import com.example.myapplication.plugin.events.Event

data class SaveFormEvent(
    val form: Form,
    val type: SaveType = SaveType.SAVE_FORM_LOCAL,
) : Event

enum class SaveType (val title: String){
    SAVE_FORM_LOCAL("SaveFormLocal"), SAVE_FORM_SERVER("SaveFormServer");
}

data class Form(
    val id: String,
    val title: String,
)