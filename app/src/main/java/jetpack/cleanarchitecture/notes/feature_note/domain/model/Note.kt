package jetpack.cleanarchitecture.notes.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import jetpack.cleanarchitecture.notes.ui.theme.BlueNote
import jetpack.cleanarchitecture.notes.ui.theme.GrayNote
import jetpack.cleanarchitecture.notes.ui.theme.GreenNote
import jetpack.cleanarchitecture.notes.ui.theme.RedNote

@Entity
data class Note(
    val title : String,
    val content : String,
    val timestamp : Long,
    val color : Int,
    @PrimaryKey val id : Int? = null
) {
    // companion object is like a static member
    companion object {
        val notesColor = listOf(BlueNote, RedNote, GreenNote, GrayNote)
    }
}

class InvalidNoteException(message : String) : Exception(message)
