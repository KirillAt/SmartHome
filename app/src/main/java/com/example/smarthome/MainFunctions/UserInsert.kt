package com.example.smarthome.MainFunctions

@kotlinx.serialization.Serializable
data class UserInsert(
    val profile_id: String,  // Идентификатор профиля для вставки пользователя
    val username: String,  // Имя пользователя для вставки
    val avatar: String?  // Путь к аватару пользователя для вставки (может быть null)
)