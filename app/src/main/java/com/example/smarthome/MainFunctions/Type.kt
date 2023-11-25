package com.example.smarthome.MainFunctions

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val type_id: Int,  // Идентификатор типа
    val image: String,  // Путь к изображению
    val base_name: String,  // Базовое название
    val values: Int,  // Значения типа
    val value_name: String,  // Название значения
    val switch_type: Int  // Тип переключателя
)