package com.clooy.toybox.feature.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.feature.dashboard.DashboardNavigationEvent.OnEnterExhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitItem
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitName
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
                ExhibitItem(
                    exhibit = ExhibitName.ExhibitA,
                    description = "Description",
                    isActive = true,
                ),
                ExhibitItem(
                    exhibit = ExhibitName.ExhibitB,
                    description = "Description",
                    isActive = true,
                ),
                ExhibitItem(
                    exhibit = ExhibitName.ExhibitC,
                    description = "Description",
                    isActive = true,
                )
            )
        )
    )
) : ViewModel() {
    private val data: Flow<List<ExhibitItem>> = exhibitListRepository.exhibitsStream

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
