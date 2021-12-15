package ru.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

/**
 * Адаптер для [RecyclerView]
 *
 * @property headers Лист объектов Model
 */
class NotesListAdapter(private val headers: List<Model>, private val onClickListener: ItemClickListener) :
    RecyclerView.Adapter<NotesListAdapter.MyViewHolder>() {

    /**
     * Класс элемента списка [RecyclerView]
     *
     * @property View элемент списка
     */
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val headerTextView: TextView = itemView.findViewById(R.id.headerTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_of_notes, parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        bind(holder, position)
    }

    override fun getItemCount() = headers.size

    private fun bind(holder: MyViewHolder, position: Int) {
        holder.headerTextView.text = headers[position].getHeader()
        holder.itemView.setOnClickListener {
            onClickListener.onClicked(headers, position)
        }
    }
}