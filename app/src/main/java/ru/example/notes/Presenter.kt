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
     *
     * @param header - заголовок заметки
     * @param content - текст заметки
     */
    fun tryToSaveNote(header: String, content: String) {

        if (header.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
            return
        }

        if (content.isEmpty()) {
            view?.showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
            return
        }

        saveNote(header, content)

        if (model.getHeader().isNotEmpty() && model.getContent().isNotEmpty()) {
            view?.showToast(TypesMessages.TOAST_SAVE_SUCCESS.message)
            view?.clearFields()
        } else if (model.getHeader().isEmpty()) {
            view?.showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
        } else if (model.getContent().isEmpty()) {
            view?.showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
        }
    }

    private fun saveNote(header: String, content: String) {
        model.addHeader(header)
        model.addContent(content)
    }
}