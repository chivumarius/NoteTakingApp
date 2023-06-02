package com.chivumarius.notetakingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.chivumarius.notetakingapp.database.NoteDatabase
import com.chivumarius.notetakingapp.databinding.ActivityMainBinding
import com.chivumarius.notetakingapp.fragments.repository.NoteRepository
import com.chivumarius.notetakingapp.viewmodel.NoteViewModel
import com.chivumarius.notetakingapp.viewmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {
    // ♦ "Initialization" of the "binding" Variable:
    lateinit var binding: ActivityMainBinding

    // ♦ "Initializing" the "noteViewModel" Variable:
    lateinit var  noteViewModel : NoteViewModel


    // ♦ The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ♦ cALLING THE fUNCTION:
        setUpViewModel()
    }


    // ♦ The "setUpViewModel()" Method:
    private fun setUpViewModel() {
       // ♦♦ CONNECTING:  "REPOSITORY"  WITH THE "VIEWMODEL" ♦♦
       // ♦♦            & "VIEWMODEL"  WITH THE "VIEW"       ♦♦

        // ♦ Creating the "noteRepository" Variable
        val noteRepository = NoteRepository(NoteDatabase(this))

        // ♦ Creating the "viewModelProviderFactory" Variable
        //      → to "Linking" the "viewModelProviderFactory"
        //      → with the "noteRepository"
        //      → which connects with "database":
        val viewModelProviderFactory = NoteViewModelFactory(application, noteRepository)

        // ♦ Connecting the "noteViewModel" with the "viewModelProviderFactory"
        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory)
            .get(NoteViewModel::class.java)
    }
}