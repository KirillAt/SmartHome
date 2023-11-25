package com.example.smarthome.MainFunctions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.room_adapter
import com.example.smarthome.Add.AddRoom
import com.example.smarthome.Device.DevicesList
import com.example.smarthome.Registration.Profile
import com.example.smarthome.R
import com.example.smarthome.utils.SupaBacebd
import com.example.smarthome.utils.UserMethods
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.lang.StringBuilder

class MainScreen : AppCompatActivity() {

    // Список элементов комнат
    var roomItems: ArrayList<RoomList> = ArrayList<RoomList>()
    // Массив комнат в формате JSON
    var room_array: JSONArray = JSONArray()
    // RecyclerView для отображения списка комнат
    var rooms: RecyclerView? = null

    // Метод, вызываемый при создании активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Установка layout для этой активности
        setContentView(R.layout.activity_main_screen)
        // Нахождение RecyclerView по id и установка ему LayoutManager
        rooms = findViewById<RecyclerView>(R.id.list)
        rooms!!.layoutManager = GridLayoutManager(this, GridLayoutManager.VERTICAL)

        // Корутина для асинхронных операций
        lifecycleScope.launch {
            // Установка адреса домовладения из пользовательских методов
            findViewById<TextView>(R.id.main_adress).text = UserMethods().getHome()!!.adress
            // Получение списка комнат из базы данных через библиотеку postgrest
            val test = SupaBacebd.getClient1().postgrest["rooms"]
                .select(
                    columns = Columns.raw("""rome_id, name, r_types ( r_type_id, base_name, image )"""
                    )
                ){  Room::home_id eq UserMethods().getHome()!!.home_id }
            // Запись результатов запроса в строку и конвертация в JSON Array
            val buf = StringBuilder()
            var line = test.body.toString()
            buf.append(line).append("\n")
            room_array = JSONArray(buf.toString())
            // Добавление элементов комнат из JSON
            addItemsFromJSON()
        }
    }

    // Метод для перехода в настройки
    fun setting(view: View) {
        val int = Intent(this, Profile::class.java)
        startActivity(int)
        finish()
    }

    // Метод для добавления комнат из JSON массива в списки для адаптера
    private fun addItemsFromJSON(){
        try{
            lifecycleScope.launch {
                for (i in 0 until room_array.length()) {
                    var itemObj: JSONObject = room_array.getJSONObject(i)
                    var testType = itemObj.getJSONObject("r_types")
                    // Загрузка картинки комнаты из хранилища
                    var img = SupaBacebd.getClient1().storage["test"].downloadPublic(testType.getString("image"))
                    var name : String
                    // Проверка, если имя комнаты не указано, используется стандартное имя типа комнаты
                    if(itemObj.getString("name") == "null")
                        name = testType.getString("base_name")
                    else
                        name = itemObj.getString("name")
                    // Создание экземпляра RoomList и добавление в список комнат
                    var catalog: RoomList = RoomList(
                        name,
                        img,
                        itemObj.getInt("rome_id")
                    )
                    roomItems += catalog
                }
                // Создание адаптера для RecyclerView и установка его для RecyclerView
                val adapter = room_adapter(roomItems, room_adapter.OnClickListener{ room -> goRoom(room.id)})
                rooms!!.adapter = adapter
                // Обновление адаптера
                adapter.notifyDataSetChanged()
            }
        } catch (e: Exception){
            Log.e("ERROE", e.toString())
        }
    }

    // Метод для перехода в выбранную комнату
    fun goRoom(room_id : Int){
        Log.e("ROOMID", room_id.toString())
        // Установка выбранной комнаты в пользовательских методах
        UserMethods().setSelectedRoom(room_id)
        // Переход к списку устройств выбранной комнаты
        val int = Intent(this, DevicesList::class.java)
        startActivity(int)
    }

    // Метод вызывается при нажатии кнопки для добавления комнаты
    fun addR(view: View) {
        val intent = Intent(this, AddRoom::class.java)
        startActivity(intent)
        finish()
    }

    // Закомментированная функция для выбора комнаты
    /*fun selectRoom(view: View) {
        view.
    }*/

}