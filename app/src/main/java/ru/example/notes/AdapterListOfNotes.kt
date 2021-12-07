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
class AdapterListOfNotes(private val headers: List<Model>) :
    RecyclerView.Adapter<AdapterListOfNotes.MyViewHolder>() {

    /**
     * Класс элемента списка [RecyclerView]
     *
     * @property View элемент списка
     */
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var headerTextView: TextView? = null

        init {
            headerTextView = itemView.findViewById(R.id.headerTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_of_notes, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        bind(holder, position)

    }

    override fun getItemCount() = headers.size

    private fun bind(holder: MyViewHolder, position: Int) {

        holder.headerTextView?.text = headers[position].getHeader()
        holder.itemView.setOnClickListener {

            val activity: MainActivity = holder.itemView.context as MainActivity

            val fragmentAlsoAdded =
                activity.supportFragmentManager.popBackStackImmediate("FragmentListOfNotes", 1)

            if (!fragmentAlsoAdded) {

                val fragmentInfoNote = FragmentInfoNote.newInstance(
                    headers[position].getHeader(),
                    headers[position].getContent(),
                    headers[position].getDate()
                )

                activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentListOfNotes, fragmentInfoNote)
                    .addToBackStack("FragmentListOfNotes")
                    .commit()
            }
        }
    }
}