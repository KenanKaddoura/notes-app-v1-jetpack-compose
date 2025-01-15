package jetpack.cleanarchitecture.notes.feature_note.domain.util

sealed class OrderType {
    object Descending : OrderType()
    object Ascending : OrderType()
}