package jetpack.cleanarchitecture.notes.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jetpack.cleanarchitecture.notes.feature_note.data.data_source.NoteDatabase
import jetpack.cleanarchitecture.notes.feature_note.data.repository.NoteRepositoryImpl
import jetpack.cleanarchitecture.notes.feature_note.domain.repository.NoteRepository
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.AddNoteUseCase
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.DeleteNoteUseCase
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.GetNoteUseCase
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.GetNotesUseCase
import jetpack.cleanarchitecture.notes.feature_note.domain.use_case.NoteUseCases
import javax.inject.Singleton

// Dagger Hilt Setup

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app : Application) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build() // Database Initialised
    }


    @Provides
    @Singleton
    fun provideNoteRepository(db : NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(repository : NoteRepository) : NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }
}