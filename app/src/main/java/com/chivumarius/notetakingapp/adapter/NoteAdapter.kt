package com.chivumarius.notetakingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chivumarius.notetakingapp.databinding.NoteLayoutBinding
import com.chivumarius.notetakingapp.fragments.HomeFragmentDirections
import com.chivumarius.notetakingapp.model.Note
import java.util.*

// ♦ The "NoteAdapter" Class
//      → which "Extend" the "RecyclerView.Adapter" Class:
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    // ♦ The "NoteViewHolder" Class
    //      → which "Extend" the "RecyclerView.Adapter" Class:
    class NoteViewHolder(val itemBinding: NoteLayoutBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    // ♦ Using the "DiffUtil" Class
    //      → can "Calculate" the "Difference" between "2 Lists"
    //      → and "Output" a "Difference" of "Updated Operations"
    //      → that Convert the "First List" into the "Second One":
    private val differCallback = object : DiffUtil.ItemCallback<Note>(){
        // ♦ The "areItemsTheSame()" Method:
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.noteBody == newItem.noteBody &&
                    oldItem.noteTitle == newItem.noteTitle
        }

        // ♦ The "areContentsTheSame()" Method:
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    // ♦ The "AsyncListDiffer()"
    //      → will "Handle" the "Synchronization" of "All Data":
    val differ = AsyncListDiffer(this, differCallback)


    // ♦ The "onCreateViewHolder" Method::
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
            )
        )
    }


    // ♦ The "onBindViewHolder()" Method::
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        // ♦ Getting the "Position" for the "Current List"
        val currentNote = differ.currentList[position]

        // ♦ Getting the "Note Title":
        holder.itemBinding.tvNoteTitle.text = currentNote.noteTitle
        // ♦ Getting the "Note Body":
        holder.itemBinding.tvNoteBody.text = currentNote.noteBody

        // ♦ Using "Random()" Class
        //      → in order to Generate Random Number:
        val random = Random()

        // ♦ Generating "Color Value"
        //   → for the "color" Object:
        val color = Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        // ♦ Setting the "Background Color"
        //      → with the "Generated Color":
        holder.itemBinding.ibColor.setBackgroundColor(color)

        // ♦ Setting "On Click Listener"
        //      → when the "User Clicks" on the "Items":
        holder.itemView.setOnClickListener{
            // ♦ "Direction" for "Home Fragment":
            val direction = HomeFragmentDirections.
                actionHomeFragmentToUpdateNoteFragment(currentNote)

            // ♦ Setting the "direction" of "navigation":
            it.findNavController().navigate(direction)
        }
    }


    // ♦ The "getItemCount()" Method::
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}