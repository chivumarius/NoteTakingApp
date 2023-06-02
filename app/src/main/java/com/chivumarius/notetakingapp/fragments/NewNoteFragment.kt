package com.chivumarius.notetakingapp.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.chivumarius.notetakingapp.MainActivity
import com.chivumarius.notetakingapp.R
import com.chivumarius.notetakingapp.adapter.NoteAdapter
import com.chivumarius.notetakingapp.databinding.FragmentNewNoteBinding
import com.chivumarius.notetakingapp.model.Note
import com.chivumarius.notetakingapp.viewmodel.NoteViewModel


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {
    // ♦ Initialization of the "binding" Variable:
    private var _binding : FragmentNewNoteBinding? = null
    // ♦ Getting the "binding":
    private val binding get() = _binding!!

    // ♦ Initialization of the "notesViewModel" Variable:
    private lateinit var notesViewModel : NoteViewModel
    // ♦ Initialization of the "noteAdapter" Variable:
    private lateinit var noteAdapter: NoteAdapter

    // ♦ Declaration of the "mView" Variable:
    private lateinit var mView: View


    // ♦ The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    // ♦ The "onCreateView()" Method:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // ♦ Using the "_binding":
        _binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }


    // ♦ The "onViewCreated()" Method:
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ♦ Initializing the "ViewModel":
        notesViewModel = (activity as MainActivity).noteViewModel

        // ♦ Initializing the "mView":
        mView = view
    }


    // ♦ The "savenote()" Method:
    private fun savenote(view: View){
        // ♦ Getting "Data" → for "noteTitle" and "noteBody":
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()

        // ♦ Checking: If the "Note Title" is Not "Empty":
        if (noteTitle.isNotEmpty()){
            // ♦ Creating a New "Note" Object:
            val note = Note(0, noteTitle, noteBody)

            // ♦ Adding the "note" Object:
            notesViewModel.addNote(note)

            // ♦ "Toast Message":
            Toast.makeText(mView.context,
                "Note Saved Successfully",
                Toast.LENGTH_LONG).show()

            // ♦ "Navigate" Back
            //      → from the "New Note Fragment"
            //      → to the "HomeFragment":
            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        } else {
            // ♦ "Toast Message":
            Toast.makeText(
                mView.context,
                "Please enter note Title",
                Toast.LENGTH_LONG).show()
        }
    }

    // ♦ The "onCreateOptionsMenu()" Method
    //      → Used to "Display" the "Menu"
    //      → "Inside" the "Fragments":
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // ♦ "Clearing" the "Menu":
        menu.clear()
        // ♦ "Displaying" the "Menu New Note":
        inflater.inflate(R.menu.menu_new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    // ♦ The "searchNote()" Method:
    override fun onDestroy() {
        super.onDestroy()

        // ♦ We "Stop" the "Binding":
        _binding = null
    }


    // ♦ The "onOptionsItemSelected()" Method:
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // ♦ "Checking" the "Condition":
        when(item.itemId){
            // ♦ "Saving" the "Note":
            R.id.menu_save -> {
                savenote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}