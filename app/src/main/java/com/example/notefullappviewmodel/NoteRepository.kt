package com.example.notefullappviewmodel

import androidx.lifecycle.LiveData

//deals with database

class NoteRepository (val db:NotesDatabase){

    suspend fun insertNote(note:NoresTable){
        db.getNotesDao().insertNote(note)
    }

    fun getAlltNote():LiveData<List<NoresTable>>{
        return db.getNotesDao().getAll()
    }

    suspend fun delNote(note:NoresTable){
        db.getNotesDao().delNote(note)
    }

    suspend fun updateNote(note:NoresTable){
        db.getNotesDao().updateNote(note)
    }

}