package ru.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

/**
 * Активность с заголовком и текстом заметки
 */
class MainActivity : AppCompatActivity(), NoteView {

    private var presenter: Presenter? = null

    private lateinit var headerView: EditText
    private lateinit var contentView: EditText
    private lateinit var saveButton: Button
    private lateinit var aboutButton: Button
    private lateinit var shareButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hideFragmentInfoNote()
        fillListOfNotes()
        initViews()
        presenter = Presenter(this)

    }

    private fun fillListOfNotes() {

        val defaultListOfNotes = mutableListOf<Model>()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewListOfNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterListOfNotes(defaultListOfNotes)
    }

    @SuppressLint("SimpleDateFormat")
    private fun initViews() {

        headerView = findViewById(R.id.headerView)
        contentView = findViewById(R.id.contentView)
        saveButton = findViewById(R.id.Button_Save)
        aboutButton = findViewById(R.id.Button_About)
        shareButton = findViewById(R.id.Button_Share)

        val newDateFormat = SimpleDateFormat("dd.M.yyyy hh:mm:ss")

        saveButton.setOnClickListener {

            presenter?.tryToSaveNote(
                headerView.text.toString(),
                contentView.text.toString(),
                newDateFormat.format(Date())
            )

        }

        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener {
            shareNote(
                headerView.text.toString(),
                contentView.text.toString()
            )
        }
    }

    override fun showToast(typeMessage: String) {
        Toast.makeText(this, typeMessage, Toast.LENGTH_SHORT).show()
    }

    override fun clearFields() {
        headerView.text.clear()
        contentView.text.clear()
    }

    private fun shareNote(header: String, content: String) {

        if (header.isEmpty()) {
            showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
            return
        }

        if (content.isEmpty()) {
            showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
            return
        }

        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            val delimiter = ":"
            putExtra(Intent.EXTRA_TEXT, "$header$delimiter\n$content")
        })
    }

    private fun hideFragmentInfoNote() {

        val fragmentInfoNote: FragmentInfoNote =
            supportFragmentManager.findFragmentById(R.id.fragmentInfoNote) as FragmentInfoNote

        this.supportFragmentManager
            .beginTransaction()
            .hide(fragmentInfoNote)
            .commit()

    }

    override fun updateRecyclerView(listModels: MutableList<Model>) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewListOfNotes)
        recyclerView.adapter = AdapterListOfNotes(listModels)
    }
}

