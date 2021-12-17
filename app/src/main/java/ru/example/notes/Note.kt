package ru.example.notes

/**
 * Класс заметки
 */
class Note {

    private var header: String = ""
    private var content: String = ""
    private var date: String = ""

    /**
     * Функция записывает текст заметки в поле модели
     *
     * @param content - текст заметки
     */
    fun addContent(content: String) {
        this.content = content
    }

    /**
     * Функция возвращает значение текста заметки из поля модели
     */
    fun getContent() = content

    /**
     * Функция записывает текст заголовка заметки в поле модели
     *
     * @param header - заголовок заметки
     */
    fun addHeader(header: String) {
        this.header = header
    }

    /**
     * Функция возвращает значение заголовка заметки из поля модели
     */
    fun getHeader() = header

    /**
     * Функция записывает дату заметки в поле модели
     *
     * @param date - дата заметки
     */
    fun addDate(date: String) {
        this.date = date
    }

    /**
     * Функция возвращает дату заметки из поля модели
     */
    fun getDate() = date
}