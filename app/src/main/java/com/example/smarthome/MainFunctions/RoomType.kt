package com.example.smarthome.MainFunctions

import kotlinx.serialization.Serializable


@Serializable
data class RoomType(
    val r_type_id: Int,  // Идентификатор типа комнаты
    val base_name: String,  // Базовое название комнаты
    val image: String  // Путь к изображению комнаты
)