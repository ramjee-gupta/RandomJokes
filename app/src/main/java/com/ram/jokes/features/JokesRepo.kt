package com.ram.jokes.features

import com.ram.jokes.utils.retrofit.ApiService

class JokesRepo(private val api: ApiService) {
    suspend fun getJokes(): String = api.getJokes()
}