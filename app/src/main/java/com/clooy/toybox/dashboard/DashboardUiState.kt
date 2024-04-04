package com.clooy.toybox.dashboard

import com.clooy.toybox.dashboard.exhibit.data.ExhibitItem

sealed interface DashboardUiState {
    object Loading : DashboardUiState
    data class Success(val data: DashboardUiData) : DashboardUiState
}

data class DashboardUiData(
    val exhibits: List<ExhibitItem>
)
