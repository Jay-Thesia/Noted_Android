package com.example.noted

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//use android app architecture so first make room database -> repo -> livedata -> activity
class MainActivity : AppCompatActivity(), RvAdapterClick {

    private lateinit var viewModel: NotesViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var etText: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvNotes)
        etText = findViewById(R.id.etnotes)

        val layout = LinearLayoutManager(this)
        recyclerView.layoutManager = layout

         val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter


        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NotesViewModel::class.java]
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }
        })
    }

    override fun onItemClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Deleted Note ${note.text}", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = etText.text.toString()

        if (noteText.isNotEmpty()) {
            viewModel.insertNote(Notes(noteText))
            Toast.makeText(this, "Inserted Note $noteText", Toast.LENGTH_SHORT).show()
        }

        etText.setText("")
    }
}