package com.dev777popov.mynoteskotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.mynoteskotlin.R
import com.dev777popov.mynoteskotlin.databinding.ItemNoteBinding
import com.dev777popov.mynoteskotlin.model.Color
import com.dev777popov.mynoteskotlin.model.Note

class MainAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val ui: ItemNoteBinding = ItemNoteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NoteViewHolder(ui)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(notes[position])


    inner class NoteViewHolder(view: ItemNoteBinding) :
        RecyclerView.ViewHolder(view.root) {
        var ui: ItemNoteBinding = ItemNoteBinding.bind(itemView)

        fun bind(note: Note) = with(ui) {

            title.text = note.title
            body.text = note.note

            val color = when (note.color) {
                Color.WHITE -> R.color.color_white
                Color.VIOLET -> R.color.color_violet
                Color.YELLOW -> R.color.color_yello
                Color.RED -> R.color.color_red
                Color.PINK -> R.color.color_pink
                Color.GREEN -> R.color.color_green
                Color.BLUE -> R.color.color_blue
            }

            root.setBackgroundColor(ContextCompat.getColor(root.context, color))
            root.setOnClickListener { onItemClickListener.onItemClick(note) }
            btnDelete.setOnClickListener { onItemClickListener.onItemDelete(note) }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
        fun onItemDelete(note: Note)
    }
}