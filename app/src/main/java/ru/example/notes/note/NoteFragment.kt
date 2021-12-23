package ru.example.notes.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.example.notes.databinding.NoteFragmentBinding

/**
 * Класс фрагмента данных заметки
 */
class NoteFragment : Fragment() {

    private lateinit var binding: NoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteFragmentBinding.inflate(inflater, container, false)
        arguments?.let {
            binding.header.text = it.getString(ARG_HEADER)
            binding.content.text = it.getString(ARG_CONTENT)
            binding.date.text = it.getString(ARG_DATE)
        }
        return binding.root
    }

    companion object {

        private const val ARG_HEADER = "header"
        private const val ARG_CONTENT = "content"
        private const val ARG_DATE = "date"

        /**
         * Создает новую инстанцию фрагмента заметки
         */
        @JvmStatic
        fun newInstance(header: String, content: String, date: String) =
            NoteFragment().apply {
                arguments = bundleOf(
                    ARG_HEADER to header,
                    ARG_CONTENT to content,
                    ARG_DATE to date)
            }
    }
}