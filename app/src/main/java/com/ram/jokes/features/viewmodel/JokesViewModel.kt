package com.ram.jokes.features.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ram.jokes.db.JokesDao
import com.ram.jokes.db.JokesEntity
import com.ram.jokes.features.JokesRepo
import com.ram.jokes.utils.retrofit.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class JokesViewModel(private val repo: JokesRepo, private val dbDao: JokesDao) : ViewModel() {

    private val _apiState = MutableLiveData<Resource<String>>()
    val apiState: LiveData<Resource<String>> = _apiState

    private val _jokesList = MutableLiveData<List<JokesEntity>>()
    val jokeList: LiveData<List<JokesEntity>> = _jokesList

    init {
        getJokes()
        _apiState.postValue(Resource.Loading())
    }

    fun getJokesFromDb() = viewModelScope.launch {
        _jokesList.postValue(dbDao.getJokes())
    }

    private fun getJokes() = viewModelScope.launch {
        try {
            var jokes = repo.getJokes()
            _apiState.postValue(Resource.Success(jokes))
        } catch (exception: NetworkErrorException) {
            _apiState.postValue(Resource.Error("Please connect to network"))
        } catch (exception: Exception) {
            _apiState.postValue(Resource.Error(exception.message.toString()))
        }


    }

    fun saveJokesToDB(jokes: List<JokesEntity>) {
        viewModelScope.launch() {
            // delete old jokes
            dbDao.deleteAll()
            // add new jokes
            dbDao.insertJoke(jokes)
        }

    }

    val countt = viewModelScope.launch() {
        while (isActive) {
            delay(60 * 1000) // 60*1000 for 1 minute
            getJokes()
        }
    }
}