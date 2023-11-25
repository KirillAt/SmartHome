package com.example.smarthome.Device

// Импорт аннотации для поддержки сериализации данных
import kotlinx.serialization.Serializable

// Класс данных для вставки нового устройства
@Serializable
data class DeviceInsert(
    val room_id: Int,            // Идентификатор комнаты, в которой находится устройство
    val name: String?,           // Название устройства (может быть null)
    val id: String,              // Уникальный идентификатор устройства
    val type_id: Int,            // Идентификатор типа устройства
    val power_state: Boolean = false, // Состояние питания устройства (по умолчанию выключено)
    val value1: Int = 1          // Дополнительное числовое значение устройства (по умолчанию 1)
)