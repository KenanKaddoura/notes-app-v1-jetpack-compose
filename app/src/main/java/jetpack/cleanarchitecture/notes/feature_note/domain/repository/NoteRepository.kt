package jetpack.cleanarchitecture.notes.feature_note.domain.repository

import jetpack.cleanarchitecture.notes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow


// The def.s are on the data layer repo.
interface NoteRepository {

    fun getNotes() : Flow<List<Note>>

    suspend fun getNoteById(id : Int) : Note?

    suspend fun insertNote(note: Note)

    suspend fun deleteNote(note : Note)
}