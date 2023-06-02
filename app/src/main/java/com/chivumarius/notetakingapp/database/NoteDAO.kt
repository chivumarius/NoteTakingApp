package com.chivumarius.notetakingapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chivumarius.notetakingapp.model.Note

// ♦ Annotate as "@Dao" ("Data Access Object")
@Dao
interface NoteDAO {
    // ♦ "Insert" Functionality:
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // ♦ "Update" Functionality:
    @Update
    suspend fun updateNote(note: Note)

    // ♦ "Delete" Functionality:
    @Delete
    suspend fun deleteNote(note: Note)

    // ♦ "Selecting All in the Notes Table → Descending Order According to Id" Functionality:
    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes() : LiveData<List<Note>>

    // ♦ "Selecting All in the Notes Table with the Condition" Functionality:
    @Query("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteBody LIKE :query")
    fun searchNote(query: String?) : LiveData<List<Note>>
}