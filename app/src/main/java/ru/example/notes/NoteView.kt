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
     * Функция обновляет список в RecyclerView
     */
    fun updateRecyclerView(listModels: MutableList<Model>)

    /**
     * Функция позволяет поделиться заметкой
     */
    fun share(header: String, content: String)
}

