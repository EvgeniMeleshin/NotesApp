package ru.example.notes

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

        saveButton.setOnClickListener {

            presenter?.tryToSaveNote(
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

}