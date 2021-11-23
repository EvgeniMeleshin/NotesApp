package ru.example.notes

/**
 * Модель данных заметки
 */
class Model {

    private var header: String = ""
    private var content: String = ""

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
    fun getContent(): String {
        return content
    }

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
    fun getHeader(): String {
        return header
    }

}