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
     * @param date - дата заметки
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
        view?.showToast(TypesMessages.TOAST_SAVE_SUCCESS.message)
        view?.clearFields()
        view?.updateRecyclerView(model.getList())

    }

    private fun saveNote(header: String, content: String, date: String) {

        val newModel = Model()

        newModel.addHeader(header)
        newModel.addContent(content)
        newModel.addDate(date)

        model.getList().add(newModel)
    }
}