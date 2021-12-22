package ru.example.notes


/**
 * Класс Presenter для [NoteView], реализует логику сохранения
 * в модель заголовка и текста заметки
 *
 * @property view View к которой получаем доступ через интерфейс
 */
class Presenter(private val view: NoteView?) {

    private val model = Model()

    /**
     * Функция проверяет корректность введенных данных
     * и сохраняет заголовок и текст заметки
     * @param header заголовок заметки
     * @param content текст заметки
     * @param date дата заметки
     */
    fun tryToSaveNote(header: String, content: String, date: String) {
        if (header.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
            return
        }
        if (content.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
            return
        }
        saveNote(header, content, date)
        view?.afterSaveNote(TypesMessages.TOAST_SAVE_SUCCESS.message, model.getList())
    }

    private fun saveNote(header: String, content: String, date: String) {
        val newNote = Note(header, content, date)
        model.getList().add(newNote)
    }

    /**
     * Функция проверяет корректность введенных данных
     * и открывает диалог выбора внешнего приложения
     * @param header заголовок заметки
     * @param content текст заметки
     */
    fun tryToShareNote(header: String, content: String){
        if (header.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
            return
        }
        if (content.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
            return
        }
        view?.share(header, content)
    }
}