package com.ram.jokes.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jokes_table")
data class JokesEntity(
    @PrimaryKey
    @ColumnInfo(name = "joke")
    val jokes: String
)
