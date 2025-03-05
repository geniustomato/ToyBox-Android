package com.clooy.toybox.feature.dashboard.presentation.dashboard.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.event.DashboardScreenEvent

@Composable
internal fun DashboardScaffold(
    modifier: Modifier = Modifier,
    settingsData: DashboardToolbar.Settings,
    onDashboardToolbarEvent: (DashboardScreenEvent.Toolbar) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { DashboardToolbar(
            settingsData = settingsData,
            onDashboardToolbarEvent = onDashboardToolbarEvent
        ) },
        content = content,
    )
}
