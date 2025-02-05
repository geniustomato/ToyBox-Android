package com.clooy.toybox.feature.dashboard

import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId

sealed interface DashboardUiState {
    data object Loading : DashboardUiState
    data class Success(val data: DashboardUiData) : DashboardUiState
}

sealed interface DashboardNavigationEvent {
    data class OnEnterExhibit(val exhibitId: ExhibitId) : DashboardNavigationEvent
}

data class DashboardUiData(
    val exhibits: List<Exhibit>
)
