package ru.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Активность с заголовком и текстом заметки
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

