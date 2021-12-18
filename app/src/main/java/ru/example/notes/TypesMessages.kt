package ru.example.notes

/**
 * Класс перечисления вариантов сообщений
 */
enum class TypesMessages(val message: String) {

    TOAST_SAVE_SUCCESS("Заметка сохранена"),
    TOAST_HEADER_IS_EMPTY("Заголовок не заполнен"),
    TOAST_CONTENT_IS_EMPTY("Текст заметки не заполнен");
}