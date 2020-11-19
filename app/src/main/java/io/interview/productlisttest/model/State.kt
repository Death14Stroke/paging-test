package io.interview.productlisttest.model

sealed class State {
    data class Loading(
            val loadedItems: Int = 0,
            val totalItems: Int = 0
    ) : State()

    object Success : State()
}