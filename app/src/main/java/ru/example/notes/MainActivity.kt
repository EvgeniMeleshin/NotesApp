package ru.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.example.notes.list.NotesListFragment

/**
 * Активность с заголовком и текстом заметки
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, NotesListFragment())
            .commit()

    }
}

