package com.chivumarius.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chivumarius.notetakingapp.model.Note

// ♦ "Annotate" as "@Database":
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){

    // ♦ The "getNoteDao()" Function:
    abstract fun getNoteDao() : NoteDAO

    // ♦ The "Companion" Object
    //      → is similar to "Static Declarations"
    //      → used to Design a Singular Pattern
    //      → returning a "Single Instance" of this "Object"
    //      → during the "Runtime":
    companion object{
        // ♦ By "@Volatile"Annotating
        //      → Any "Changes" to this "Instance"
        //      → will be "Visible"
        //      → to "Any Thread":
        @Volatile
        private var instance: NoteDatabase? = null
        private val LOCK = Any()

        // ♦ The "invoke()" Function:
        operator fun invoke(context: Context) = instance ?:
            synchronized(LOCK){
                instance ?:
                // ♦ Calling the "createDatabase()" Function:
                createDatabase(context).also{
                    instance = it
                }
            }


        // ♦ The "createDatabase()" Function:
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()
    }
}