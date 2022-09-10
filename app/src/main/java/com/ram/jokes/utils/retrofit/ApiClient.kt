package com.ram.jokes.utils.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BASE_URL = "https://geek-jokes.sameerkumar.website"

fun getGson(): Gson = GsonBuilder()
    .setLenient()
    .create()

fun getRetrofit(gson: Gson) = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun provideRetrofitInstance(retrofit: Retrofit) =
    retrofit.create(ApiService::class.java)