package ru.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.example.notes.databinding.NotesListFragmentBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Класс фрагмента
 */
class NotesListFragment : Fragment(), NoteView {

    private lateinit var binding: NotesListFragmentBinding
    private var presenter: Presenter? = null
    private val defaultListOfNotes = mutableListOf<Model>()

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
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = NotesListAdapter(defaultListOfNotes, object : ItemClickListener{
            override fun onClicked(fragment: NoteFragment) {
                val fragmentAlsoAdded =
                    activity?.supportFragmentManager?.popBackStackImmediate("FragmentListOfNotes", 1)
                if (!fragmentAlsoAdded!!) {
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(R.id.notesListFragment, fragment)
                        ?.addToBackStack("FragmentListOfNotes")
                        ?.commit()
                }
            }
        })
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

    override fun updateRecyclerView(listModels: MutableList<Model>) {
        binding.recyclerView.adapter = NotesListAdapter(listModels, object : ItemClickListener{
            override fun onClicked(fragment: NoteFragment) {
                val fragmentAlsoAdded =
                    activity?.supportFragmentManager?.popBackStackImmediate("FragmentListOfNotes", 1)
                if (!fragmentAlsoAdded!!) {
                    activity?.supportFragmentManager
                            ?.beginTransaction()
                            ?.replace(R.id.notesListFragment, fragment)
                            ?.addToBackStack("FragmentListOfNotes")
                            ?.commit()
                }
            }
        })
    }
}