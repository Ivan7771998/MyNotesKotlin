package com.dev777popov.mynoteskotlin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.mynoteskotlin.adapter.MainAdapter
import com.dev777popov.mynoteskotlin.databinding.ActivityMainBinding
import com.dev777popov.mynoteskotlin.model.Note
import com.dev777popov.mynoteskotlin.vm.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = MainAdapter(object : MainAdapter.OnItemClickListener {
            override fun onItemClick(note: Note) {
                openNoteScreen(note)
            }

            override fun onItemDelete(note: Note) {
                deleteNote(note = note)
            }
        })

        ui.mainRecycler.adapter = adapter

        viewModel.viewState().observe(this, Observer { t ->
            t?.let { adapter.notes = it }
        })

        ui.fab.setOnClickListener { openNoteScreen(null) }

    }

    private fun openNoteScreen(note: Note?) {
        val intent = NoteActivity.newIntent(this, note)
        startActivity(intent)
    }

    private fun deleteNote(note: Note) {
        viewModel.deleteNote(note)
    }
}