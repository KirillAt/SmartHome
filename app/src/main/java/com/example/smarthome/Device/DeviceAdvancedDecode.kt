package com.example.smarthome.Device

import kotlinx.serialization.Serializable

// Аннотация @Serializable указывает, что этот класс может быть сериализован (преобразован в формат, который можно легко сохранить или передать).

@Serializable
class DeviceAdvancedDecode(
    val device_id: Int,         // Идентификатор устройства
    val value2: Int,            // Второе числовое значение
    val value_name: String,     // Название значения (строковое)
    val switch_type: Int        // Тип переключателя (числовой)
)