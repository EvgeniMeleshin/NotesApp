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
     * */
    fun tryToSaveNote(header: String, content: String) {

        if (header.isEmpty()) {
            view?.showMessageIfHeaderIsEmpty()
            return
        }

        if (content.isEmpty()) {
            view?.showMessageIfContentIsEmpty()
            return
        }

        saveNote(header, content)

        if (model.getHeader().isNotEmpty() && model.getContent().isNotEmpty()) {
            view?.showMessageSuccess()
            view?.clearFields()
        } else if (model.getHeader().isEmpty()) {
            view?.showMessageIfHeaderIsEmpty()
        } else if (model.getContent().isEmpty()) {
            view?.showMessageIfContentIsEmpty()
        }
    }

    private fun saveNote(header: String, content: String) {
        model.addHeader(header)
        model.addContent(content)
    }
}