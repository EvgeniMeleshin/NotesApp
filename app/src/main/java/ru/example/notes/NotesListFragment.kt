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
    private var actualListNotes = defaultListOfNotes

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
            putExtra(Intent.EXTRA_TEXT, "$header:\n$content")
        })
    }

    override fun updateNotesList(listNotes: MutableList<Note>) {
        actualListNotes = listNotes
        binding.recyclerView.adapter = NotesListAdapter(listNotes, object : ItemClickListener{
            override fun onClicked(headers: List<Note>, position: Int) {
                replaceFragment(headers, position)
            }
        })
    }

    private fun replaceFragment(headers: List<Note>, position: Int){
        val item = headers[position]
        val fragment = NoteFragment.newInstance(
            item.getHeader(),
            item.getContent(),
            item.getDate())
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.add(R.id.container, fragment)
                ?.addToBackStack("NotesListFragment")
                ?.commit()
    }
}