package com.clooy.toybox.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId.*
import com.clooy.toybox.feature.dashboard.exhibit.repository.ExhibitListDataSource
import com.clooy.toybox.feature.dashboard.exhibit.repository.ExhibitListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    // TODO I think this needs some DI magic w/ Hilt
    private val exhibitListRepository: ExhibitListRepository = ExhibitListRepository(
        exhibitListDataSource = ExhibitListDataSource(
            exhibitList = listOf(
                Exhibit(
                    id = ExhibitA,
                    name = "Exhibit A",
                    description = "Description",
                    isActive = true,
                ),
                Exhibit(
                    id = ExhibitB,
                    name = "Exhibit B",
                    description = "Description",
                    isActive = true,
                ),
                Exhibit(
                    id = ExhibitC,
                    name = "Exhibit C",
                    description = "Description",
                    isActive = true,
                )
            )
        )
    )
) : ViewModel() {
    private val data: Flow<List<Exhibit>> = exhibitListRepository.exhibitsStream

    val uiState: StateFlow<DashboardUiState> =
        data.map { exhibits ->
            DashboardUiState.Success(
                data = DashboardUiData(
                    exhibits = exhibits
                )
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = DashboardUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )
}
