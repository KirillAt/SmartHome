package com.example.smarthome.MainFunctions

// Импорт сериализуемой аннотации для использования Kotlin Serialization
import kotlinx.serialization.Serializable

// Класс, представляющий комнату в умном доме
@Serializable
data class Room(
    val room_id: Int,        // Идентификатор комнаты
    val home_id: Int,        // Идентификатор дома, к которому принадлежит комната
    val name: String?,       // Название комнаты (может быть null)
    val r_type_id: Int       // Идентификатор типа комнаты
)

/*
   Комментарии:
   room_id - Идентификатор комнаты в умном доме.
   home_id - Идентификатор дома, к которому принадлежит данная комната.
   name - Название комнаты (может быть null), если не задано.
   r_type_id - Идентификатор типа комнаты, связанный с другими данными о типах комнат.
*/