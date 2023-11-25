package com.example.smarthome.Device

import kotlinx.serialization.Serializable

// Аннотация @Serializable указывает, что этот класс может быть сериализован и десериализован
@Serializable
class DeviceAdvanced(val device_id: Int, val value2: Int) {
    // Класс, представляющий устройство с продвинутой информацией
    // device_id: Идентификатор устройства (целое число)
    // value2: Дополнительное значение устройства (целое число)
}