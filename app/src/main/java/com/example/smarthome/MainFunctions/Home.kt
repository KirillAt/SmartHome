package com.example.smarthome.MainFunctions

import kotlinx.serialization.Serializable

// Определение структуры данных "Дом"
@Serializable
data class Home(val home_id : Int, val adress : String, val profile_id : String)
// Класс "Дом" содержит поля: идентификатор дома, адрес и идентификатор профиля