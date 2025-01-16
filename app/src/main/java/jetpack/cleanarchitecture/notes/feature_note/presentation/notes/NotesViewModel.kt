package jetpack.cleanarchitecture.notes.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jetpack.cleanarchitecture.notes.feature_note.domain.model.Note
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.GetNotesUseCase
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.NoteUseCases
import jetpack.cleanarchitecture.notes.feature_note.domain.util.NoteOrder
import jetpack.cleanarchitecture.notes.feature_note.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NoteUseCases
) : ViewModel() {

    // Feature's States :
    private val _state = mutableStateOf(NotesState())
    val state : State<NotesState> = _state

    private var recentlyDeletedNote : Note? = null

    private var getNotesJob : Job? = null

    init {
        getNotes(NoteOrder.Date(OrderType.Descending))
    }

    fun onEvent(event : NotesEvent) {

        when(event) {
            is NotesEvent.Order -> {

                if (event.noteOrder::class == state.value.noteOrder::class &&
                    event.noteOrder.orderType == state.value.noteOrder.orderType)
                {
                    return
                }
                getNotes(event.noteOrder)

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNoteUseCase(event.note)
                    recentlyDeletedNote = event.note
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {

                    notesUseCases.addNoteUseCase(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null


//                    recentlyDeletedNote?.let { notesUseCases.addNoteUseCase(it) } (Option 2)
                }
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder)  {

        getNotesJob?.cancel()

        getNotesJob = notesUseCases.getNotesUseCase(noteOrder)
            .onEach { notes ->
                _state.value = state.value.copy (
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }

}