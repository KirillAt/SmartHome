package com.example.smarthome.MainFunctions

import android.graphics.drawable.Drawable
import android.media.Image
import kotlinx.serialization.Serializable

@Serializable
data class RoomList(
    val name: String,  // Имя комнаты
    val icon: ByteArray,  // Иконка комнаты в виде массива байтов
    val id: Int  // Идентификатор комнаты
)