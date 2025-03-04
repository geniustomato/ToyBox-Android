package com.clooy.toybox.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
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
        exhibitListDataSource = ExhibitListDataSource()
    )
) : ViewModel() {
    private val data: Flow<List<Exhibit>> = exhibitListRepository.exhibitsStream

    val uiState: StateFlow<DashboardUiState> =
        data.map { exhibits ->
            DashboardUiState.Success(
                data = DashboardUiData(
                    exhibits = exhibits,
                    settings = DashboardToolbarData.Settings(
                        selectedViewType = ViewType.LIST
                    )
                )
            )
        }.stateIn(
            scope = viewModelScope,
            initialValue = DashboardUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )
}
