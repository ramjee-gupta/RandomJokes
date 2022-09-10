package com.ram.jokes.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JokesEntity::class], version = 1)
abstract class JokesDatabase : RoomDatabase() {

   abstract fun getJokesDao() : JokesDao
}