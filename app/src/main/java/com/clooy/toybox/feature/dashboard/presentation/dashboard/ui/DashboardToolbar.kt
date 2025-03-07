package com.clooy.toybox.feature.dashboard.presentation.dashboard.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.clooy.toybox.R
import com.clooy.toybox.core.designsystem.component.icon.ToyBoxIcons
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.event.DashboardScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DashboardToolbar(
    modifier: Modifier = Modifier,
    settingsData: DashboardToolbar.Settings,
    onDashboardToolbarEvent: (DashboardScreenEvent.Toolbar) -> Unit,
) {
    var settings by rememberSaveable { mutableStateOf(settingsData) }
    var showSettingsDialog by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
        title = { /* No Title */ },
        actions = {
            IconButton(onClick = { showSettingsDialog = true }) {
                Icon(
                    imageVector = ToyBoxIcons.Settings,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    )

    if (showSettingsDialog) {
        DashboardSettingsDialog(
            settings = settings,
            onSettingsUpdated = { updatedSettingsData ->
                settings = updatedSettingsData
                onDashboardToolbarEvent(
                    DashboardScreenEvent.Toolbar.OnSettingsUpdate(
                        updatedSettingsData
                    )
                )
            },
            onDismiss = { showSettingsDialog = false }
        )
    }
}
