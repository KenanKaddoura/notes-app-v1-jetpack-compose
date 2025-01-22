package jetpack.cleanarchitecture.notes.feature_note.domain.use_case

// we wrap all use cases of note feature into this class, and then we inject this class into our view model
// we can extend it as needed
data class NoteUseCases (
    val getNotesUseCase: GetNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase
)