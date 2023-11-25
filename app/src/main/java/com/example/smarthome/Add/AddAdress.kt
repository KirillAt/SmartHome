package com.example.smarthome.Add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.smarthome.MainFunctions.MainScreen
import com.example.smarthome.utils.UserMethods
import com.example.smartlab.smarthome.R
import kotlinx.coroutines.launch

class AddAdress : AppCompatActivity() {
    // Метод onCreate вызывается при создании Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Устанавливается макет для данной Activity
        setContentView(R.layout.activity_add_adress)
    }

    // Функция addHome1 вызывается при нажатии на кнопку в макете
    fun addHome1(view: View) {
        // Получаем адрес из поля EditText по его id
        val adr = findViewById<EditText>(R.id.edit_adress).text.toString()
        // Создаем новый intent для перехода на MainScreen
        val int = Intent(this, MainScreen::class.java)
        // Создаем регулярное выражение для проверки корректности адреса

        // Проверяем, соответствует ли введенный адрес шаблону

            // Если адрес соответствует, выполняем следующий код асинхронно
            lifecycleScope.launch {
                // Получаем информацию о пользователе
                val user = UserMethods().getUser()
                // Добавляем адрес в список адресов пользователя
                UserMethods().addHome(user!!.id, adr)
                // Переходим на MainScreen
                startActivity(int)
                // Записываем в лог адрес дома
                Log.e("HOMETEST", UserMethods().getHome()!!.adress)
                // Заканчиваем текущую Activity
                finish()
            }
        }
    }
