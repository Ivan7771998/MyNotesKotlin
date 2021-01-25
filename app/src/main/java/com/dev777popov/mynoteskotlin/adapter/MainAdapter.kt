package com.dev777popov.mynoteskotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.mynoteskotlin.R
import com.dev777popov.mynoteskotlin.databinding.ItemNoteBinding
import com.dev777popov.mynoteskotlin.model.Note

class MainAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemNoteBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_note, parent, false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }
}

class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) = with(itemView) {
        binding.apply {
            title.text = note.title
            body.text = note.note
        }
        setBackgroundColor(note.color)
    }
}