package com.chivumarius.notetakingapp.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.chivumarius.notetakingapp.MainActivity
import com.chivumarius.notetakingapp.R
import com.chivumarius.notetakingapp.adapter.NoteAdapter
import com.chivumarius.notetakingapp.databinding.FragmentHomeBinding
import com.chivumarius.notetakingapp.model.Note
import com.chivumarius.notetakingapp.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) , SearchView.OnQueryTextListener{
    // ♦ Initialization of the "binding" Variable:
    private var _binding : FragmentHomeBinding? = null
   // ♦ Getting the "binding":
    private val binding get() = _binding!!

    // ♦ Initialization of the "notesViewModel" Variable:
    private lateinit var notesViewModel : NoteViewModel
    // ♦ Initialization of the "noteAdapter" Variable:
    private lateinit var noteAdapter: NoteAdapter

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // ♦ The "onViewCreated()" Method:
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ♦ Initializing the "ViewModel":
        notesViewModel = (activity as MainActivity).noteViewModel

        // ♦ Calling the Function:
        setUpRecyclerView()

        // ♦ Setting "On Click Listener"
        binding.fabAddNote.setOnClickListener{
            // ♦ "Navigate" from "homeFragmen" to the "newNoteFragment":
            it.findNavController().navigate(
                R.id.action_homeFragment_to_newNoteFragment
            )
        }
    }

    // ♦ The "setUpRecyclerView()" Method:
    private fun setUpRecyclerView() {
        // ♦ Creating an "Instance" from "NoteAdapter()":
        noteAdapter = NoteAdapter()

        // ♦ Using the ".apply " function to add paths
        binding.recyclerView.apply {
            // ♦ Adding the "StaggeredGridLayoutManager()"
            //      → for "layoutManager":
            layoutManager = StaggeredGridLayoutManager(
                2,      // ► The "Number" of "Grids"/"Columns"
                StaggeredGridLayoutManager.VERTICAL     // ► The "Orientation"
            )
            // ♦ Setting:
            setHasFixedSize(true)
            adapter = noteAdapter
        }

        // ♦ "Activity":
        activity?.let {
            // ♦ Calling "observe()" → for "All Notes":
            notesViewModel.getAllNotes().observe(
                viewLifecycleOwner
            ) { note ->
                noteAdapter.differ.submitList(note)
                // ♦ Calling the Method:
                updateUI(note)
            }
        }
    }


    // ♦ The "updateUI()" Method:
    private fun updateUI(note: List<Note>?) {
        // ♦ Checking: If the "Note" is Not "null":
        if (note != null) {
            // ♦ Checking: If the "Note" is Not "Empty":
            if (note.isNotEmpty()){
                // ♦ Removing the "Card View":
                binding.cardView.visibility = View.GONE
                // ♦ Displaying "New Note" inside the "Recycler View":
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                // ♦ Displaying "No Note are Available" in the "Card View":
                binding.cardView.visibility = View.VISIBLE
                // ♦ Removing the "Note" from the "Recycler View":
                binding.recyclerView.visibility = View.GONE

            }
        }
    }


    // ♦ The "onCreateOptionsMenu()" Method
    //      → Used to "Display" the "Menu"
    //      → "Inside" the "Fragments":
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // ♦ "Clearing" the "Menu":
        menu.clear()
        // ♦ "Displaying" the "Home Menu":
        inflater.inflate(R.menu.home_menu, menu)

        // ♦ Finding the "menu_search" Id:
        val mMenuSearch = menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled = false
        mMenuSearch.setOnQueryTextListener(this)
    }


    // ♦ The "onQueryTextSubmit()" Method:
    override fun onQueryTextSubmit(query: String?): Boolean {
        // ♦ Calling the Function:
        searchNote(query)
        return false
    }


    // ♦ The "onQueryTextChange()" Method:
    override fun onQueryTextChange(newText: String?): Boolean {
        // ♦ Checking: If the "Note" is Not "null":
        if (newText != null){
            // ♦ Calling the Function:
            searchNote(newText)
        }
        return true
    }


    // ♦ The "searchNote()" Method:
    private fun searchNote(query: String?){
        // ♦ "Searching" the "Query"
        val searchQuery = "%$query"

        // ♦ Calling "observe()" → for "Search Note"
        //      → from the "notesViewModel":
        notesViewModel.searchNote(searchQuery).observe(
            this
        ) { list -> noteAdapter.differ.submitList(list) }
    }


    // ♦ The "searchNote()" Method:
    override fun onDestroy() {
        super.onDestroy()
       // ♦ We "Stop" the "Binding":
        _binding = null
    }
}