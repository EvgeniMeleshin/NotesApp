package ru.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentInfoNote : Fragment() {

    private lateinit var elHeader: TextView
    private lateinit var elContent: TextView
    private lateinit var elDate: TextView

    private var header: String? = null
    private var content: String? = null
    private var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            header = it.getString(ARG_HEADER)
            content = it.getString(ARG_CONTENT)
            date = it.getString(ARG_DATE)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_info_note, container, false)
        elHeader = view.findViewById(R.id.infoNoteHeader)
        elContent = view.findViewById(R.id.infoNoteContent)
        elDate = view.findViewById(R.id.infoNoteDate)
        elHeader.text = header
        elContent.text = content
        elDate.text = date
        return view
    }

    companion object {

        private const val ARG_HEADER = "header"
        private const val ARG_CONTENT = "content"
        private const val ARG_DATE = "date"

        @JvmStatic
        fun newInstance(header: String, content: String, date: String) =
            FragmentInfoNote().apply {
                arguments = Bundle().apply {
                    putString(ARG_HEADER, header)
                    putString(ARG_CONTENT, content)
                    putString(ARG_DATE, date)
                }
            }
    }

}