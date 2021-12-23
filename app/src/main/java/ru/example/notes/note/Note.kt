package ru.example.notes.note

/**
 * Класс заметки
 */
data class Note(private var header: String, private var content: String, private var date: String) {

    /**
     * Функция возвращает значение текста заметки из поля модели
     */
    fun getContent() = content

    /**
     * Функция возвращает значение заголовка заметки из поля модели
     */
    fun getHeader() = header

    /**
     * Функция возвращает дату заметки из поля модели
     */
    fun getDate() = date
}