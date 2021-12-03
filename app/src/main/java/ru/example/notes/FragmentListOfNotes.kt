package ru.example.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

private const val ARG_HEADER = "header"
private const val ARG_CONTENT = "content"

class FragmentListOfNotes: Fragment() {

    private var header: String? = null
    private var content: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            header = it.getString(ARG_HEADER)
            content = it.getString(ARG_CONTENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_notes, container, false)
    }

}