package com.example.smarthome.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.smarthome.MainFunctions.MainScreen
import com.example.smarthome.R
import com.example.smarthome.utils.UserMethods
import kotlinx.coroutines.launch

class Profile : AppCompatActivity() {

    // При создании активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // Задаем содержимое экрана из layout файла

        // Выполняем асинхронный код в контексте жизненного цикла активити
        lifecycleScope.launch {
            val user = UserMethods().getUser() // Получаем пользователя
            findViewById<EditText>(R.id.email_edit).setText(user!!.email) // Задаем email в EditText
            findViewById<EditText>(R.id.login_edit).setText(UserMethods().getUsername()) // Задаем имя пользователя в EditText
            findViewById<EditText>(R.id.adress_edit).setText(UserMethods().getHome()!!.adress) // Задаем адрес в EditText
            if(UserMethods().getAvatar() == null){ // Если аватар не найден
                Log.e("PROFTEST", "AVATAR NOT FOUND") // Выводим ошибку в лог
            }
        }
    }

    // Обработчик кнопки возврата
    fun back(view: View) {
        startActivity(Intent(this, MainScreen::class.java)) // Стартуем MainScreen активити
        finish() // Закрываем текущее активити
    }

    // Обработчик кнопки выхода из профиля
    fun exit(view: View) {
        val int = Intent(this, Login::class.java) // Создаем Intent для активити аутентификации
        lifecycleScope.launch { // В асинхронном режиме
            UserMethods().logout() // Выполняем логаут
            val sPref = getSharedPreferences("login", MODE_PRIVATE).edit() // Открываем SharedPreferences для редактирования
            sPref.remove("email") // Удаляем email
            sPref.remove("password") // Удаляем пароль
            sPref.remove("code") // Удаляем код
            sPref.apply() // Применяем изменения
            startActivity(int) // Стартуем активити аутентификации
            finish() // Закрываем текущее активити
        }
    }

    // Обработчик сохранения профиля
    fun save(view: View) {
        val mail = findViewById<EditText>(R.id.email_edit).text.toString() // Считываем email из EditText
        val uname = findViewById<EditText>(R.id.login_edit).text.toString() // Считываем имя пользователя из EditText
        val adress = findViewById<EditText>(R.id.adress_edit).text.toString() // Считываем адрес из EditText
        lifecycleScope.launch { // Асинхронно
            UserMethods().changeProfile(mail, uname, adress) // Обновляем данные профиля
        }
    }
}