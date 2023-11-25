package com.example.smarthome.MainFunctions

import kotlinx.serialization.Serializable

// Определение структуры данных "Дом для вставки"
@Serializable
data class HomeInsert(val address: String, val profileId: String)
// Класс "HomeInsert" содержит поля: адрес и идентификатор профиля