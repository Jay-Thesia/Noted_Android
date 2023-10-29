package com.example.noted

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao:NotesDAO) {

    val allNotes: LiveData<List<Notes>> = noteDao.getAllNotes()

    suspend fun insert(note:Notes){
        noteDao.insert(note)
    }

    suspend fun delete(note: Notes){ 
        noteDao.delete(note)
    }
}