package com.ram.jokes.features

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ram.jokes.databinding.ActivityMainBinding
import com.ram.jokes.db.JokesEntity
import com.ram.jokes.features.viewmodel.JokesViewModel
import com.ram.jokes.utils.retrofit.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var jokesAdapter: JokesAdapter

    val viewModel: JokesViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
        viewModel.getJokesFromDb()
        setUpObserver()

    }

    private fun setUpUi() {
        binding.apply {
            jokesAdapter = JokesAdapter()
            val layout = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            binding.jokesListView.layoutManager = layout
            binding.jokesListView.adapter = jokesAdapter

        }
    }

    private fun setUpObserver() {
        viewModel.jokeList.observe(this) {
            it?.let { it1 -> jokesAdapter.setJokes(it1) }
        }
        viewModel.apiState.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = GONE
                }
                is Resource.Loading -> {
                        binding.progressBar.visibility = VISIBLE

                }
                is Resource.Success -> {
                    it.data?.let { it1 -> jokesAdapter.setJokes(JokesEntity(it1)) }
                    binding.progressBar.visibility = GONE

                }
            }

        }

    }

    override fun onPause() {
        viewModel.saveJokesToDB(jokesAdapter.getJokesList())
        super.onPause()
    }
}