package com.example.notefullappviewmodel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

//deals with NoteRepository

class myViewModel (app:Application) : AndroidViewModel(app){
    private val noteRepository: NoteRepository

    init {
         noteRepository= NoteRepository(NotesDatabase.getInstance(app))
    }

    fun insertNote(note:NoresTable){
        viewModelScope.launch {
            noteRepository.insertNote(note)
        }
    }

    fun updatetNote(note:NoresTable){
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }
    }

    fun delNote(note:NoresTable){
        viewModelScope.launch {
            noteRepository.delNote(note)
        }
    }

    fun geyAllnote() : LiveData<List<NoresTable>>{
        return noteRepository.getAlltNote()
    }

   /* class ViewModelFactory(val app:Application, val noteRepository: NoteRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return myViewModel(app,noteRepository) as T
        }
    }//end ViewModelFactory class*/
}