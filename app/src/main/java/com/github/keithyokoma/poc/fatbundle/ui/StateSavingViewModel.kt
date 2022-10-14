package com.github.keithyokoma.poc.fatbundle.ui

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ViewState : Parcelable

abstract class StateSavingViewModel<S : ViewState>(
    defaultState: S,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val stateMutation: MutableStateFlow<S> = MutableStateFlow(savedStateHandle[KEY_SAVE_VIEW_STATE] ?: defaultState)
    val states: StateFlow<S> = stateMutation.asStateFlow()
    val latestState: S
        get() = states.value

    protected fun updateState(newStateGenerator: (S) -> S) {
        stateMutation.value = newStateGenerator(latestState).also { newState ->
            savedStateHandle[KEY_SAVE_VIEW_STATE] = newState
        }
    }

    companion object {
        private const val KEY_SAVE_VIEW_STATE = "view_state"
    }
}