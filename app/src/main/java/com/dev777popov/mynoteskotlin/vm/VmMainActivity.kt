package com.dev777popov.mynoteskotlin.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev777popov.mynoteskotlin.model.Note
import com.dev777popov.mynoteskotlin.repo.Repository

class VmMainActivity : ViewModel() {

    private val notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()

    init {
        notes.value = Repository.notes
    }

    fun viewState(): LiveData<List<Note>> = notes
}