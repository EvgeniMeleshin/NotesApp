package ru.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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

        initViews()
        presenter =  Presenter(this)

    }

    private fun initViews() {

        headerView = findViewById(R.id.headerView)
        contentView = findViewById(R.id.contentView)
        saveButton = findViewById(R.id.Button_Save)
        aboutButton = findViewById(R.id.Button_About)
        shareButton = findViewById(R.id.Button_Share)

        saveButton.setOnClickListener {

            presenter?.tryToSaveNote(
                headerView.text.toString(),
                contentView.text.toString()
            )

        }

        aboutButton.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        shareButton.setOnClickListener{
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

        if(header.isEmpty()){
            showToast(TypesMessages.TOAST_HEADER_IS_EMPTY.message)
            return
        }

        if(content.isEmpty()){
            showToast(TypesMessages.TOAST_CONTENT_IS_EMPTY.message)
            return
        }

        startActivity(Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            val delimiter = ":"
            putExtra(Intent.EXTRA_TEXT, "$header$delimiter\n$content")
        })
    }
}