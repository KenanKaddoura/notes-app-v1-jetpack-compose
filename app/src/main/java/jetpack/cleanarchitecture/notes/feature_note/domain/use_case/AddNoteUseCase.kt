package jetpack.cleanarchitecture.notes.feature_note.domain.use_case

import jetpack.cleanarchitecture.notes.feature_note.domain.model.InvalidNoteException
import jetpack.cleanarchitecture.notes.feature_note.domain.model.Note
import jetpack.cleanarchitecture.notes.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNoteUseCase(
    private val repository: NoteRepository
) {

    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note : Note) {

        if(note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can not be empty.")
        }
        if(note.content.isBlank()) {
            throw InvalidNoteException("The content of the note can not be empty.")
        }
        repository.insertNote(note)
    }
}