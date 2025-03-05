package com.clooy.toybox.feature.dashboard.presentation.dashboard.event

import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitId

internal sealed interface DashboardScreenEvent {

    sealed interface Navigation: DashboardScreenEvent {
        data class OnEnterExhibit(val exhibitId: ExhibitId) : Navigation
    }

    sealed interface Toolbar : DashboardScreenEvent {
        data class OnSettingsUpdate(val data: DashboardToolbar.Settings) : Toolbar
    }
}
