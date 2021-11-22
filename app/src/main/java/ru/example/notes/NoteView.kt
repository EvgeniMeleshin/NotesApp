package ru.example.notes

interface NoteView {

    fun showMessageSuccess()

    fun showMessageFailed()

    fun showMessageIfHeaderIsEmpty()

    fun showMessageIfContentIsEmpty()

    fun clearFields()

}