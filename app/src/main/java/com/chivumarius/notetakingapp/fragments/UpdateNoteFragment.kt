package com.chivumarius.notetakingapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.chivumarius.notetakingapp.MainActivity
import com.chivumarius.notetakingapp.R
import com.chivumarius.notetakingapp.databinding.FragmentUpdateNoteBinding
import com.chivumarius.notetakingapp.model.Note
import com.chivumarius.notetakingapp.viewmodel.NoteViewModel


class UpdateNoteFragment : Fragment(R.layout.fragment_update_note) {
    // ♦ Initialization of the "binding" Variable:
    private var _binding : FragmentUpdateNoteBinding? = null
    // ♦ Getting the "binding":
    private val binding get() = _binding!!

    // ♦ Initialization of the "notesViewModel" Variable:
    private lateinit var notesViewModel : NoteViewModel

    // ♦ Declaration of the "currentNote" Variable:
    private lateinit var currentNote : Note

    // ♦ Since the "Update Note Fragment"
    //      → Contains "Arguments"
    //      → in "nav_graph"
    private val args: UpdateNoteFragmentArgs by navArgs()


    // ♦ The "onCreate()" Method:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    // ♦ The "onCreateView()" Method:
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // ♦ Using the "_binding":
        _binding = FragmentUpdateNoteBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    // ♦ The "onViewCreated()" Method:
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ♦ Initializing the "ViewModel":
        notesViewModel = (activity as MainActivity).noteViewModel
        // ♦ Set:
        currentNote = args.note!!   // ♦ "!!" = Not Null

        // ♦ Setting the "Text" for "noteTitle" and "noteBody":
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)

        // ♦ Setting "On Click Listener"
        //      → If the "User Update" the "Note":
        binding.fabDone.setOnClickListener{
            // ♦ Getting the "CurrentNote" with "Title" and "Body":
            val title = binding.etNoteTitleUpdate.text.toString().trim()
            val body = binding.etNoteBodyUpdate.text.toString().trim()

            // ♦ Checking: If the "Title" is Not "Empty":
            if (title.isNotEmpty()){
                // ♦ Initialize the "note" as a New "Note()" Object:
                val note = Note(currentNote.id,title, body)
                // ♦ "Updating" the "Note":
                notesViewModel.updateNote(note)
                // ♦ Navigate from "updateNoteFragment" to the "homeFragment":           4
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            } else {
                // ♦ "Toast Message":
                Toast.makeText(
                    context,
                    "Please enter note Title",
                    Toast.LENGTH_LONG).show()
            }
        }
    }


    // ♦ The "deleteNote" Method
    //      → for Displaying an "Alert Dialog"
    //      → for the "User":
    private fun deleteNote(){
        // ♦ Display "Alert Dialog" for the "User":
        AlertDialog.Builder(activity).apply {
            // ♦ "Setting" the "Title":
            setTitle("Delete Note")

            // ♦ "Setting" the "Body":
            setMessage("You want to delete this Note?")

            // ♦ "Setting" the "Positive" Button:
            setPositiveButton("Delete"){_,_ ->
                // ♦ Deleting "Current Note":
                notesViewModel.deleteNote(currentNote)

                // ♦ "Navigate" - from the "updateNoteFragment"
                //      → to the "homeFragment":
                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )
            }

            // ♦ "Setting" the "Negative" Button:
            setNegativeButton("Cancel", null)

        // ♦ "Creating" and "Displaying" the "Alert Dialog":
        }.create().show()
    }


    // ♦ The "onCreateOptionsMenu()" Method:
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // ♦ "Clearing" the "Menu":
        menu.clear()
        // ♦ "Displaying" the "Update Note Menu":
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    // ♦ The "onOptionsItemSelected()" Method:
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // ♦ "Checking" the "Condition":
        when(item.itemId){
            // ♦ "Deleting" the "Note":
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    // ♦ The "onDestroy()" Method:
    override fun onDestroy() {
        super.onDestroy()
        // ♦ We "Stop" the "Binding":
        _binding = null
    }
}