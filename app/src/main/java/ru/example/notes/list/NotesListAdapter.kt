package ru.example.notes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.example.notes.R
import ru.example.notes.note.Note

/**
 * Адаптер для [RecyclerView]
 *
 * @property headers Лист объектов Note
 * @property onClickListener слушатель нажатия для элемента списка
 */
class NotesListAdapter(private val headers: List<Note>, private val onClickListener: ItemClickListener) :
    RecyclerView.Adapter<NotesListAdapter.MyViewHolder>() {

    /**
     * Класс элемента списка заметок [RecyclerView]
     *
     * @property View элемент списка заметок
     */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)

        /**
         * Функция вызывает фрагмент NoteFragment с детальной информацией по заметке
         * @param position номер позиции списка заметок
         */
        fun bind(position: Int) {
            headerTextView.text = headers[position].getHeader()
            itemView.setOnClickListener {
                onClickListener.onClicked(headers, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_of_notes, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = headers.size
}