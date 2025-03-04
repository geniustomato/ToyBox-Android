package com.clooy.toybox.feature.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clooy.toybox.R
import com.clooy.toybox.core.designsystem.component.icon.ToyBoxIcons

@Composable
fun DashboardScaffold(
    modifier: Modifier = Modifier,
    settingsData: DashboardToolbarData.Settings,
    onDashboardToolbarEvent: (DashboardToolbarEvent) -> Unit,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardToolbar(
    modifier: Modifier = Modifier,
    settingsData: DashboardToolbarData.Settings,
    onDashboardToolbarEvent: (DashboardToolbarEvent) -> Unit,
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
                    DashboardToolbarEvent.OnDashboardSettingsUpdate(
                        updatedSettingsData
                    )
                )
            },
            onDismiss = { showSettingsDialog = false }
        )
    }
}

@Composable
private fun DashboardSettingsDialog(
    modifier: Modifier = Modifier,
    settings: DashboardToolbarData.Settings,
    onSettingsUpdated: (DashboardToolbarData.Settings) -> Unit,
    onDismiss: () -> Unit,
) {
    val viewTypes = ViewType.entries.toTypedArray()
    val (selectedViewType, onViewTypeSelected) = rememberSaveable {
        mutableStateOf(viewTypes.find { it == settings.selectedViewType } ?: viewTypes[0])
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        text = {
            Column {
                Text(
                    text = stringResource(R.string.view_as),
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                viewTypes.forEach { viewType ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            viewType == selectedViewType,
                            onClick = {
                                onViewTypeSelected(viewType)
                            })
                        Text(text = stringResource(id = viewType.label))
                    }
                }
            }
        },
        confirmButton = {
            Text(
                modifier = Modifier.clickable {
                    onSettingsUpdated(
                        settings.copy(
                            selectedViewType = selectedViewType
                        )
                    )
                    onDismiss()
                },
                text = stringResource(R.string.save)
            )
        },
    )
}
