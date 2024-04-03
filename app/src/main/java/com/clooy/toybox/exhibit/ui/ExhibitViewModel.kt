package com.clooy.toybox.exhibit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.exhibit.data.Exhibit
import com.clooy.toybox.exhibit.repository.ExhibitDataSource
import com.clooy.toybox.exhibit.repository.ExhibitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExhibitViewModel(
    // TODO I think this needs some DI magic
    private val exhibitRepository: ExhibitRepository = ExhibitRepository(
        exhibitDataSource = ExhibitDataSource(
            exhibitList = listOf(
                Exhibit(
                    name = "NameA",
                    description = "Description",
                    isActive = true,
                ),
                Exhibit(
                    name = "NameB",
                    description = "Description",
                    isActive = true,
                ),
                Exhibit(
                    name = "NameC",
                    description = "Description",
                    isActive = true,
                )
            )
        )
    )
) : ViewModel() {
    private val data: Flow<List<Exhibit>> = exhibitRepository.exhibitsStream

    // TODO I wonder how I can stop the data from reloading upon configuration change
    // I think this has something to do with the view model not instantiated independently from the composable
    val uiState: StateFlow<ExhibitsUiState> =
        data.map { exhibits ->
            ExhibitsUiState.Success(exhibits = exhibits)
        }.stateIn(
            scope = viewModelScope,
            initialValue = ExhibitsUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )
}
