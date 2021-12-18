package ru.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Класс активности с информацией о приложении
 */
class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}