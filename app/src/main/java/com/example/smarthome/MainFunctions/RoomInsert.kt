package com.example.smarthome.MainFunctions

import kotlinx.serialization.Serializable

// Класс, представляющий данные для вставки комнаты
@Serializable
data class RoomInsert(val home_id : Int, val name : String?, val r_type_id : Int)

/*
Поля класса RoomInsert:
- home_id: Идентификатор дома, к которому относится комната
- name: Необязательное имя комнаты
- r_type_id: Идентификатор типа комнаты
*/
/*
Структура данных для комнат:
- room_id: Идентификатор комнаты
- name: Название комнаты
- r_types: Типы комнат (
    - r_type_id: Идентификатор типа комнаты
    - base_name: Базовое название
)
*/