package ru.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.example.notes.databinding.NotesListFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Класс фрагмента со списком заметок
 */
class NotesListFragment : Fragment(), NoteView {

    private lateinit var binding: NotesListFragmentBinding
    private var presenter: Presenter? = null
    private val defaultListOfNotes = Model().getList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NotesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {
        presenter = Presenter(this)
        binding.saveButton.setOnClickListener {
            presenter?.tryToSaveNote(
                binding.headerView.text.toString(),
                binding.contentView.text.toString(),
                SimpleDateFormat("dd.M.yyyy hh:mm:ss").format(Date())
            )
        }
        binding.aboutButton.setOnClickListener {
            val intent = Intent(this.context, AboutActivity::class.java)
            startActivity(intent)
        }
        binding.shareButton.setOnClickListener {
            presenter?.tryToShareNote(
                binding.headerView.text.toString(),
                binding.contentView.text.toString()
            )
        }
        binding.recyclerView.adapter = NotesListAdapter(defaultListOfNotes, object : ItemClickListener{
            override fun onClicked(headers: List<Note>, position: Int) {
                replaceFragment(headers, position)
            }
        })
    }

    override fun afterSaveNote(message: String, listNotes: MutableList<Note>) {
        showToast(message)
        clearFields()
        updateNotesList(listNotes)
    }

    override fun showToast(typeMessage: String) {
        Toast.makeText(this.context, typeMessage, Toast.LENGTH_SHORT).show()
    }

    override fun clearFields() {
        binding.headerView.text.clear()
        binding.contentView.text.clear()
    }

    override fun share(header: String, content: String) {
        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            val delimiter = ":"
            putExtra(Intent.EXTRA_TEXT, "$header$delimiter\n$content")
        })
    }

    override fun updateNotesList(listNotes: MutableList<Note>) {
        binding.recyclerView.adapter = NotesListAdapter(listNotes, object : ItemClickListener{
            override fun onClicked(headers: List<Note>, position: Int) {
                replaceFragment(headers, position)
            }
        })
    }

    private fun replaceFragment(headers: List<Note>, position: Int){
        val fragmentAlsoAdded =
            activity?.supportFragmentManager?.popBackStackImmediate("FragmentListOfNotes", 1)
        if (!fragmentAlsoAdded!!) {
            val fragment = NoteFragment.newInstance(
                headers[position].getHeader(),
                headers[position].getContent(),
                headers[position].getDate())
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.notesListFragment, fragment)
                ?.addToBackStack("FragmentListOfNotes")
                ?.commit()
        }
    }
}