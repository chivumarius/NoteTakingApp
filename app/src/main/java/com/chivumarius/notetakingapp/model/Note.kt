/*
    ♦ "Parcelable"
        → is an "Interface"
        → that "Allows" the "Custom Model" or "Data Class"
        → to be "Passed" from "One Activity" to "Another".
*/
package com.chivumarius.notetakingapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// ♦ "Annotate" the "Class" as "@Entity"/"Table":
@Entity(tableName = "notes")
@Parcelize
data class Note(
    // ♦ Declaring the "Table Columns":
    // ♦ "Annotating" the "id" as the " @PrimaryKey":
    @PrimaryKey(autoGenerate = true)
    val id : Int,

    val noteTitle : String,
    val noteBody : String
): Parcelable
