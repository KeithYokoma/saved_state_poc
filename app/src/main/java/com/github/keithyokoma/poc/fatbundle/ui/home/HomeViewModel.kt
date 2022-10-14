package com.github.keithyokoma.poc.fatbundle.ui.home

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import com.github.keithyokoma.poc.fatbundle.ui.StateSavingViewModel
import com.github.keithyokoma.poc.fatbundle.ui.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class HomeListItem(
    val id: Long,
    val name: String,
    val age: Int,
    val imageUrl: String,
) : Parcelable

@Parcelize
data class HomeViewState(
    val items: List<HomeListItem> = listOf(
        HomeListItem(
            id = 0,
            name = "KY",
            age = 10,
            imageUrl = "https://dummyimage.com/300x300/ffffff/ABABAB.png&text=KY"
        )
    )
) : ViewState

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : StateSavingViewModel<HomeViewState>(HomeViewState(), savedStateHandle) {
    fun addItem() {
        updateState { state ->
            val name = getRandomString()
            val newItem = HomeListItem(
                id = (Math.random() * 100000).toLong(),
                name = name,
                age = (Math.random() * 100).toInt(),
                imageUrl = "https://dummyimage.com/300x300/ffffff/ABABAB.png&text=$name"
            )
            state.copy(items = state.items.plus(newItem))
        }
    }

    private fun getRandomString() : String {
        val allowedChars = ('A'..'Z')
        return (1..2)
            .map { allowedChars.random() }
            .joinToString("")
    }
}

