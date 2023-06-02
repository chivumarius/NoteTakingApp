package com.chivumarius.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.chivumarius.notetakingapp.model.Note
import com.chivumarius.notetakingapp.fragments.repository.NoteRepository
import kotlinx.coroutines.launch

// ♦ The "NoteViewModel" Class
//      → having "Primary Constructor"
//      → with "Application" Parameter
//      → and "noteRepository"Parameter
//      → which Extend the "AndroidViewModel" Class:
class NoteViewModel( app: Application, private val noteRepository: NoteRepository)
    : AndroidViewModel(app) {

    // ♦ The "addNote()" Function:
    fun addNote(note: Note) =
        viewModelScope.launch {
            // ♦ "Insert" the "Note"
            //      → by calling the "noteRepository":
            noteRepository.insertNote(note)
        }

    // ♦ Calling the "deleteNote()" Function:
    fun deleteNote(note: Note){
        viewModelScope.launch {
            // ♦ "Delete" the "Note"
            //      → by calling the "noteRepository":
            noteRepository.deleteNote(note)
        }
    }

    // ♦ Calling the "updateNote()" Function:
    fun updateNote(note: Note){
        viewModelScope.launch {
            // ♦ "Update" the "Note"
            //      → by calling the "noteRepository":
            noteRepository.updateNote(note)
        }
    }

    // ♦ Calling the "getAllNotes()" Function:
    //      → will "Get All the Notes"
    //      → by calling the "noteRepository":
    fun getAllNotes() = noteRepository.getAllNotes()

    // ♦ Calling the "searchNote()" Function:
    //      → will "Search" the "Note"
    //      → by calling the "noteRepository":
    fun searchNote(query : String?) =
        noteRepository.searchNote(query)
}