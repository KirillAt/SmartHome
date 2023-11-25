package com.example.smarthome.Device

import kotlinx.serialization.Serializable

// Класс, представляющий устройство
@Serializable
data class Device (
    val device_id : Int, // Идентификатор устройства
    val room_id : Int, // Идентификатор комнаты, в которой находится устройство
    val name : String?, // Название устройства (может быть null)
    val id : String, // Уникальный идентификатор устройства
    val type_id : Int, // Идентификатор типа устройства
    val power_state : Boolean, // Состояние питания устройства (включено/выключено)
    val value1 : Int // Значение 1 устройства
)