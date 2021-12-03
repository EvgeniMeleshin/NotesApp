package ru.example.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class AdapterListOfNotes(private val headers: List<Model>):
    RecyclerView.Adapter<AdapterListOfNotes.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var headerTextView: TextView? = null

        init {
            headerTextView = itemView.findViewById(R.id.headerTextView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                        R.layout.item_list_of_notes, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.headerTextView?.text = headers[position].getHeader()

        holder.itemView.setOnClickListener {

            val fragmentInfoNote
                = FragmentInfoNote.newInstance(
                    headers[position].getHeader(),
                    headers[position].getContent(),
                    headers[position].getDate())

            val activity: MainActivity = holder.itemView.context as MainActivity
            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentListOfNotes, fragmentInfoNote)
                .addToBackStack("FragmentListOfNotes")
                .commit()

        }
    }

    override fun getItemCount(): Int {
        return headers.size
    }

}