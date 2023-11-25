package com.example.smarthome.Registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.smarthome.Add.AddAdress
import com.example.smarthome.MainFunctions.MainScreen
import com.example.smarthome.R
import com.example.smarthome.utils.UserMethods
import kotlinx.coroutines.launch

class CreateCode : AppCompatActivity() {
    // Переменная для хранения пароля
    var password = ""

    // Переопределение метода onCreate при создании активити
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Установка макета для активити из файла activity_code_create.xml
        setContentView(R.layout.activity_code_create)
    }

    // Метод, вызываемый при клике на кнопку с цифрой или кнопку удаления (dlt)
    fun num_click(view: View) {
        // Проверка, является ли нажатая кнопка кнопкой удаления
        if(view.getTag().toString() != "dlt")
        // Если не кнопка удаления, то добавить цифру к текущему паролю
            setPassword(view.getTag().toString().toInt())
        else
        // Если кнопка удаления, то удалить последний символ из пароля
            dltPassword()
    }
    fun dltPassword(){
        if(password.length != 0){
            when(password.length){
                1 -> findViewById<ImageView>(R.id.dot_0).setImageResource(R.drawable.dot_0)
                2 -> findViewById<ImageView>(R.id.dot_1).setImageResource(R.drawable.dot_0)
                3 -> findViewById<ImageView>(R.id.dot_2).setImageResource(R.drawable.dot_0)
                4 -> findViewById<ImageView>(R.id.dot_3).setImageResource(R.drawable.dot_0)
            }
            password = password.substring(0, password.length-1)
        }
        Toast.makeText(this, password, Toast.LENGTH_SHORT).show()
    }

    // Функция для установки пароля
    fun setPassword(num: Int) {
        // Добавляем число к текущему паролю
        password += num
        // Если длина пароля достигла 4 символов
        if (password.length == 4) {
            // Получаем редактор для работы с SharedPreferences
            val sPref = getSharedPreferences("login", MODE_PRIVATE).edit()
            // Сохраняем пароль с использованием метода saveCode из класса UserMethods
            UserMethods().saveCode(sPref, password)
            // Создаем намерение для перехода на экран AddAddress
            val add = Intent(this, AddAdress::class.java)
            // Создаем намерение для перехода на экран MainScreen
            val main = Intent(this, MainScreen::class.java)
            // Запускаем корутину для асинхронного выполнения следующего блока кода
            lifecycleScope.launch {
                // Если домашний адрес пользователя не установлен
                if (UserMethods().getHome() == null) {
                    // Запускаем экран добавления адреса и завершаем текущий экран
                    startActivity(add)
                    finish()
                } else {
                    // Иначе запускаем основной экран и завершаем текущий экран
                    startActivity(main)
                    finish()
                }
            }
        }
        when(password.length){
            1 -> findViewById<ImageView>(R.id.dot_0).setImageResource(R.drawable.dot_1)
            2 -> findViewById<ImageView>(R.id.dot_1).setImageResource(R.drawable.dot_1)
            3 -> findViewById<ImageView>(R.id.dot_2).setImageResource(R.drawable.dot_1)
            4 -> findViewById<ImageView>(R.id.dot_3).setImageResource(R.drawable.dot_1)
        }
    }
}