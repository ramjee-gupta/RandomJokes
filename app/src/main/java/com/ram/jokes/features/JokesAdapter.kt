package com.ram.jokes.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ram.jokes.databinding.SingleJokesRowLayoutBinding
import com.ram.jokes.db.JokesEntity

class JokesAdapter : RecyclerView.Adapter<JokesAdapter.JokesHolder>() {

    var jokesList = ArrayList<JokesEntity>(10)

    inner class JokesHolder(var jokeView: SingleJokesRowLayoutBinding) : RecyclerView.ViewHolder(jokeView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesHolder {
        return JokesHolder(SingleJokesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: JokesHolder, position: Int) {
        holder.run {
            with(jokesList){
                jokeView.jokesView.text = jokesList[position].jokes
            }
        }
    }

    override fun getItemCount(): Int = jokesList.size

    fun setJokes(joke: JokesEntity){
        if(jokesList.size < 10){
            jokesList.add(joke)
            notifyItemInserted(jokesList.size - 1)
        } else {
            jokesList.removeAt(0)
            notifyItemRemoved(0)
            jokesList.add(joke)
            notifyItemInserted(jokesList.size - 1)
        }
    }
    fun setJokes(joke: List<JokesEntity>){
        jokesList = joke as ArrayList<JokesEntity>
        notifyDataSetChanged()
    }

    fun getJokesList() : List<JokesEntity> = jokesList
}