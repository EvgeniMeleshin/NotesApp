package ru.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

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

        findViewById<EditText>(R.id.headerView).also { headerView = it }
        findViewById<EditText>(R.id.contentView).also { contentView = it }
        findViewById<Button>(R.id.Button_Save).also { saveButton = it }
        saveButton.setOnClickListener {

            presenter?.tryToSaveNote(
                headerView.text.toString(),
                contentView.text.toString()
            )

        }
    }

    override fun showMessageIfHeaderIsEmpty() {
        showToast(getString(R.string.toastHeaderIsEmpty))
    }

    override fun showMessageIfContentIsEmpty() {
        showToast(getString(R.string.toastContentIsEmpty))
    }

    override fun showMessageSuccess() {
        showToast(getString(R.string.toastSaveSuccess))
    }

    override fun showMessageFailed() {
        showToast(getString(R.string.toastSaveFiled))
    }

    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun clearFields() {
        headerView.text.clear()
        contentView.text.clear()
    }

}