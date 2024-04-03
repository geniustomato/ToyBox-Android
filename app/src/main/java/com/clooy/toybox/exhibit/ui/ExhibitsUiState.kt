package com.clooy.toybox.exhibit.ui

import com.clooy.toybox.exhibit.data.Exhibit

sealed interface ExhibitsUiState {
    object Loading : ExhibitsUiState
    data class Success(val exhibits: List<Exhibit>) : ExhibitsUiState
}
