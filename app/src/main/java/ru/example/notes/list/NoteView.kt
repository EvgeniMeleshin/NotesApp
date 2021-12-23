package ru.example.notes.list

import ru.example.notes.note.Note

/**
 * Интерфейс для взаимодействия со View через Presenter
 */
interface NoteView {

    /**
     * Функция выводит сообщение с переданным ее тестом
     * @param typeMessage текст сообщения
     */
    fun showToast(typeMessage: String)

    /**
     * Функция очищает поле заголовка и поле текста заметки
     */
    fun clearFields()

    /**
     * Функция обновляет список заметок
     * @param listNotes список заметок
     */
    fun updateNotesList(listNotes: MutableList<Note>)

    /**
     * Функция позволяет поделиться заметкой
     * @param header заголовок заметки
     * @param content заметка
     */
    fun share(header: String, content: String)

    /**
     * Выполняется после успешного сохранения заметки
     * @param message текст сообщения
     * @param listNotes список заметок
     */
    fun afterSaveNote(message: String, listNotes: MutableList<Note>)
}

