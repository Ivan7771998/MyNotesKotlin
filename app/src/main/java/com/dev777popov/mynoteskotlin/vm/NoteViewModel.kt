package com.dev777popov.mynoteskotlin.vm

import androidx.lifecycle.ViewModel
import com.dev777popov.mynoteskotlin.model.Note
import com.dev777popov.mynoteskotlin.repo.Repository

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}