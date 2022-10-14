package com.github.keithyokoma.poc.fatbundle.ui.dashboard

import androidx.lifecycle.SavedStateHandle
import com.github.keithyokoma.poc.fatbundle.ui.StateSavingViewModel
import com.github.keithyokoma.poc.fatbundle.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class DashboardViewState(
    val count: Int = 0,
) : ViewState

@HiltViewModel
class DashboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : StateSavingViewModel<DashboardViewState>(DashboardViewState(), savedStateHandle) {
    fun incrementCount() {
        updateState { state ->
            state.copy(count = state.count + 1)
        }
    }

    fun decrementCount() {
        updateState { state ->
            state.copy(count = state.count - 1)
        }
    }
}