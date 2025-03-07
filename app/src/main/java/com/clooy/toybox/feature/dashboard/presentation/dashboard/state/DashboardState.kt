package com.clooy.toybox.feature.dashboard.presentation.dashboard.state

import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardUiModel

internal sealed interface DashboardState {
    data object Loading : DashboardState
    data class Success(val data: DashboardUiModel) : DashboardState
}
