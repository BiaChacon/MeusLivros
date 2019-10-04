package com.biachacon.meuslivros.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.biachacon.meuslivros.model.Livro

@Database(entities = [Livro::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDao
}