package ru.example.notes

class Presenter(private val view: NoteView?) {

    private val model = Model()

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