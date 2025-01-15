package jetpack.cleanarchitecture.notes.feature_note.domain.use_case

import jetpack.cleanarchitecture.notes.feature_note.domain.model.Note
import jetpack.cleanarchitecture.notes.feature_note.domain.repository.NoteRepository
import jetpack.cleanarchitecture.notes.feature_note.domain.util.NoteOrder
import jetpack.cleanarchitecture.notes.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        notesOrder : NoteOrder = NoteOrder.Date(OrderType.Descending)
    ) : Flow<List<Note>> {

        return repository.getNotes().map {

            notes ->

            when(notesOrder.orderType) {
                is OrderType.Ascending -> {
                    when(notesOrder) {
                        is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        is NoteOrder.Color -> notes.sortedBy { it.color }
                    }
                }
                is OrderType.Descending -> {
                    when(notesOrder) {
                        is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                        is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        is NoteOrder.Color -> notes.sortedByDescending { it.color }
                    }
                }

            }

        }
    }
}