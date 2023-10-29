package com.example.noted

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NotesDAO {

    //we have two thread in android main and background if any heavy task
    // will run in the main thread it will stop the execution of the app that's why
    //run it in the background thread so use "suspend" it is co routine


    //to avoid redundancy in the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Notes)

    @Delete
    suspend fun delete(note:Notes)

    @Query(value = "SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}