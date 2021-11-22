package ru.example.notes

/**
 * Интерфейс для взаимодействия со View через Presenter
 * */
interface NoteView {

    /**
     * Функция выводит сообщение об успешном сохраненнии заметки
     * */
    fun showMessageSuccess()

    /**
     * Функция выводит сообщение о неудачном сохраненнии заметки
     * */
    fun showMessageFailed()

    /**
     * Функция выводит сообщение о незаполненном заголовке заметки
     * */
    fun showMessageIfHeaderIsEmpty()

    /**
     * Функция выводит сообщение о незаполненном тексте заметки
     * */
    fun showMessageIfContentIsEmpty()

    /**
     * Функция очищает поле заголовка и поле текста заметки
     * */
    fun clearFields()

}