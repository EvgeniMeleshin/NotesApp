package ru.example.notes

/**
 * Интерфейс для взаимодействия с элементом списка
 */
interface ItemClickListener {

    /**
     * Событие по клику элемента списка
     */
    fun onClicked(headers: List<Model>, position: Int)

}