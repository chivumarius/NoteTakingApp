package com.chivumarius.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chivumarius.notetakingapp.fragments.repository.NoteRepository

// ♦ The "NoteViewModelFactory" Class
//      → which Makes the "Interaction"
//      → between "View" and "Repository",
//      → having "Primary Constructor"
//      → with "Application" Parameter
//      → and "noteRepository"Parameter
//      → which Extend the "ViewModelProvider.Factory" Interface:
class NoteViewModelFactory( val app: Application,
    private val noteRepository: NoteRepository
)
    : ViewModelProvider.Factory {

    // ♦ The "create()" Method
    //      → to Link "ViewModel"
    //      → with "ViewModelFactory":
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(app, noteRepository) as T
    }
}