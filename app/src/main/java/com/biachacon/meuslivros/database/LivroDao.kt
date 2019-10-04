package com.biachacon.meuslivros.database

import androidx.room.*
import com.biachacon.meuslivros.model.Livro

@Dao
interface LivroDao {

    @Insert
    fun insert(livro: Livro): Long

    @Delete
    fun delete(livro: Livro): Int

    @Update
    fun update(livro: Livro): Int

    @Query("SELECT * FROM livro")
    fun listAll(): Array<Livro>
    
    @Query("SELECT * FROM livro WHERE id = :id")
    fun findById(id: Long): Livro

    @Query("SELECT * FROM livro WHERE titulo = :titulo")
    fun findByName (titulo: String) : Livro

}