package com.example.notefullappviewmodel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    //method to get all data:
    @Query("SELECT * FROM Notes")
    fun getAll(): LiveData<List<NoresTable>>

    //insert a row into the table
    @Insert
    fun insertNote(note:NoresTable)

    //update note
    @Update
    fun updateNote(note: NoresTable)

    //delete note row
    @Delete
    fun delNote(noteid: NoresTable)

}