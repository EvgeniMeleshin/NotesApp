package ru.example.notes

/**
 * Класс заметки
 */
class Note(_header: String, _content: String, _date: String) {

    private var header: String = _header
    private var content: String = _content
    private var date: String = _date

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