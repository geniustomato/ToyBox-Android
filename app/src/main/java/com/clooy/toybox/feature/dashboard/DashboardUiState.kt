package com.clooy.toybox.feature.dashboard

import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitItem

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Success(val data: DashboardUiData) : DashboardUiState
}

sealed interface DashboardNavigationEvent {
    data class OnEnterExhibit(val exhibit: ExhibitItem) : DashboardNavigationEvent
}

data class DashboardUiData(
    val exhibits: List<ExhibitItem>
)
