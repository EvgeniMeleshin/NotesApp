package ru.example.notes


/**
 * Модель данных заметки
 */
class Model: ModelInterface {

    private var listModels: MutableList<Note> = ArrayList<Note>()

    override fun getList() = listModels
}