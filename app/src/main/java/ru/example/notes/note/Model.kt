package ru.example.notes.note

/**
 * Модель данных заметки
 */
data class Model(private var listModels: MutableList<Note> = ArrayList<Note>()): ModelInterface {

    override fun getList() = listModels
}