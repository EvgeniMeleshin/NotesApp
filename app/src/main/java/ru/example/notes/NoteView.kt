package ru.example.notes

/**
 * Интерфейс для взаимодействия со View через Presenter
 */
interface NoteView {

    /**
     * Функция выводит сообщение с переданным ее тестом
     */
    fun showToast(typeMessage: String)

    /**
     * Функция очищает поле заголовка и поле текста заметки
     */
    fun clearFields()

    /**
     * Функция обновляет список заметок
     */
    fun updateNotesList(listNotes: MutableList<Note>)

    /**
     * Функция позволяет поделиться заметкой
     */
    fun share(header: String, content: String)

    /**
     * Выполняется после успешного сохранения заметки
     */
    fun afterSaveNote(message: String, listNotes: MutableList<Note>)
}

