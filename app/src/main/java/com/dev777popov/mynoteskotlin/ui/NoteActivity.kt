package com.dev777popov.mynoteskotlin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.dev777popov.mynoteskotlin.Constants.DATE_TIME_FORMAT
import com.dev777popov.mynoteskotlin.Constants.SAVE_DELAY
import com.dev777popov.mynoteskotlin.R
import com.dev777popov.mynoteskotlin.databinding.ActivityNoteBinding
import com.dev777popov.mynoteskotlin.model.Color
import com.dev777popov.mynoteskotlin.model.Note
import com.dev777popov.mynoteskotlin.vm.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    private var note: Note? = null
    private lateinit var viewModel: NoteViewModel
    private lateinit var ui: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setSupportActionBar(ui.toolbar)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(
                DATE_TIME_FORMAT,
                Locale.getDefault()
            ).format(note!!.lastChanged)
        } else {
            getString(R.string.new_note_title)
        }

        initView()
    }

    private fun initView() {

        ui.titleEt.doAfterTextChanged { triggerSaveNote() }
        ui.bodyEt.doAfterTextChanged { triggerSaveNote() }

        if (note != null) {
            ui.titleEt.setText(note?.title ?: "")
            ui.bodyEt.setText(note?.note ?: "")
            val color = when (note!!.color) {
                Color.WHITE -> R.color.color_white
                Color.VIOLET -> R.color.color_violet
                Color.YELLOW -> R.color.color_yello
                Color.RED -> R.color.color_red
                Color.PINK -> R.color.color_pink
                Color.GREEN -> R.color.color_green
                Color.BLUE -> R.color.color_blue
            }

            ui.toolbar.setBackgroundColor(ContextCompat.getColor(this, color))
        }
    }


    private fun triggerSaveNote() {
        if (ui.titleEt.text.toString().length < 3) return

        Handler().postDelayed({
            note = note?.copy(
                title = ui.titleEt.text.toString(),
                note = ui.bodyEt.text.toString(),
                lastChanged = Date()
            )
                ?: createNewNote()

            if (note != null) viewModel.saveChanges(note!!)
        }, SAVE_DELAY)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun createNewNote(): Note = Note(
        UUID.randomUUID().toString(),
        ui.titleEt.text.toString(),
        ui.bodyEt.text.toString()
    )


    companion object {
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"

        fun newIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            return intent
        }
    }

}