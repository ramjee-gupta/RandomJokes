package com.ram.jokes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query

@Dao
interface JokesDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertJoke(joke: List<JokesEntity>)

    @Query("SELECT * FROM jokes_table")
    suspend fun getJokes() : List<JokesEntity>

    @Query("DELETE FROM jokes_table")
    suspend fun deleteAll()
}