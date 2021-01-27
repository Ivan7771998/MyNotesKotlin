package com.dev777popov.mynoteskotlin.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev777popov.mynoteskotlin.model.Note
import com.dev777popov.mynoteskotlin.repo.Repository

class MainViewModel(private val repository: Repository = Repository) : ViewModel() {

    private val notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    init {

        repository.getNotes().observeForever {
            notes.value = it ?: repository.getNotes().value
        }
    }

    fun deleteNote(note: Note){
        repository.delete(note)
    }

    fun viewState(): MutableLiveData<List<Note>> = notes
}