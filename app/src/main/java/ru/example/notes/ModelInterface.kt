package ru.example.notes

/**
 * Интерфейс для работы с моделью
 */
interface ModelInterface {

    /**
     * Функция возвращает список заметок
     */
    fun getList(): List<Note>

}