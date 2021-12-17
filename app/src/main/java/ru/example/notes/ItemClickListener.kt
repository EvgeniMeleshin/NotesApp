package ru.example.notes

/**
 * Интерфейс для взаимодействия с элементом списка заметок
 */
interface ItemClickListener {

    /**
     * Событие по клику элемента списка заметок
     * @param headers список заметок
     * @param position номер позиции в списке
     */
    fun onClicked(headers: List<Note>, position: Int)

}