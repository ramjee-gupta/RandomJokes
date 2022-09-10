package com.ram.jokes.utils.retrofit

import retrofit2.http.GET

interface ApiService {

    @GET("/api")
    suspend fun getJokes() : String
}