package com.ram.jokes.db

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module


fun provideDatabase(application: Application): JokesDatabase =
    Room.databaseBuilder(application, JokesDatabase::class.java, "jakes_database")
        .fallbackToDestructiveMigration().build()

fun provideDao(db: JokesDatabase) = db.getJokesDao()

val dbModule = module {
    single {
        provideDatabase(get())
    }

    single {
        provideDao(get())
    }
}