package com.dev777popov.mynoteskotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dev777popov.mynoteskotlin.R
import com.dev777popov.mynoteskotlin.adapter.MainAdapter
import com.dev777popov.mynoteskotlin.vm.VmMainActivity

class MainActivity : AppCompatActivity() {

    lateinit var adapter: MainAdapter
    lateinit var viewModel: VmMainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.mainRecycler)

        viewModel = ViewModelProvider(this).get(VmMainActivity::class.java)
        adapter = MainAdapter()

        recycler.adapter = adapter

        viewModel.viewState().observe(this, Observer { t ->
            t?.let { adapter.notes = it }
        })

    }
}