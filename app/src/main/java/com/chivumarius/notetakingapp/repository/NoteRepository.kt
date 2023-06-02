/*
    ♦ The "Repository" Functionality
        → is to "Link" to the "DAO"
        → when we use "Room Database"
        → and to "Detect" Where the "CRUD Operations"
        → are "Performed From".
* */
package com.chivumarius.notetakingapp.fragments.repository

import com.chivumarius.notetakingapp.database.NoteDatabase
import com.chivumarius.notetakingapp.model.Note

//  ♦ The "NoteRepository" Class
//      → Get All "NoteDAO" Functions
//      → through the "NoteDatabase":    → to "Those Existing" in "NOTE DAO"
class NoteRepository(private val db: NoteDatabase) {
    // ♦ Creating "All Functions Similar"

    // ♦ The "insertNote()" Function:
    suspend fun insertNote(note : Note) = db.getNoteDao().insertNote(note)

    // ♦ The "deleteNote()" Function:
    suspend fun deleteNote(note : Note) = db.getNoteDao().deleteNote(note)

    // ♦ The "updateNote()" Function:
    suspend fun updateNote(note : Note) = db.getNoteDao().updateNote(note)

    // ♦ The "getAllNotes()" Function:
    fun getAllNotes() = db.getNoteDao().getAllNotes()

    // ♦ The "searchNote()" Function:
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)
}