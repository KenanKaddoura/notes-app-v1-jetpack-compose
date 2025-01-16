package jetpack.cleanarchitecture.notes.feature_note.presentation.notes

import jetpack.cleanarchitecture.notes.feature_note.domain.model.Note
import jetpack.cleanarchitecture.notes.feature_note.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder : NoteOrder) : NotesEvent()
    data class DeleteNote(val note : Note) : NotesEvent()
    object ToggleOrderSection : NotesEvent()
    object RestoreNote : NotesEvent()
}

// deleted note, opennote, change the order, open the section, close the section, restoreNote