package com.example.smarthome.MainFunctions

@kotlinx.serialization.Serializable
data class User(
    val profile_id: String,  // Идентификатор профиля пользователя
    val username: String,  // Имя пользователя
    val avatar: String?  // Путь к аватару пользователя (может быть null)
)