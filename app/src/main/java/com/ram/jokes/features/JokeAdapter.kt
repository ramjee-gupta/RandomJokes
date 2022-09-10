package com.ram.jokes.features


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ram.jokes.databinding.SingleJokesRowLayoutBinding
import com.ram.jokes.db.JokesEntity

class JokeAdapter : ListAdapter<JokesEntity, JokeAdapter.JokesHolder>(DiffUtil()) {

    inner class JokesHolder(var jokeView: SingleJokesRowLayoutBinding) :
        RecyclerView.ViewHolder(jokeView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesHolder {
        return JokesHolder(
            SingleJokesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: JokesHolder, position: Int) {
        with(holder) {
            getItem(position).apply {
                jokeView.jokesView.text = this.jokes
            }
        }
    }

    fun setJokes(joke: JokesEntity){

        if(currentList.size < 10){
            currentList.add(joke)
        } else {
            currentList.removeAt(0)
            currentList.add(joke)
        }
    }
    fun setJokes(joke: List<JokesEntity>){
        submitList(joke as ArrayList<JokesEntity>)

    }

    fun getJokesList() : List<JokesEntity> = currentList

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<JokesEntity>() {
        override fun areItemsTheSame(oldItem: JokesEntity, newItem: JokesEntity): Boolean {
            return oldItem.jokes == newItem.jokes
        }

        override fun areContentsTheSame(oldItem: JokesEntity, newItem: JokesEntity): Boolean {
            return oldItem == newItem
        }

    }

}