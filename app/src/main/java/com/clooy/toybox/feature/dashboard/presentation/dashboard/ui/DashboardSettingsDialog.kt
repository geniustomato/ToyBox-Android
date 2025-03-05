package com.clooy.toybox.feature.dashboard.presentation.dashboard.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clooy.toybox.R
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitViewType

@Composable
internal fun DashboardSettingsDialog(
    modifier: Modifier = Modifier,
    settings: DashboardToolbar.Settings,
    onSettingsUpdated: (DashboardToolbar.Settings) -> Unit,
    onDismiss: () -> Unit,
) {
    val exhibitViewTypes = ExhibitViewType.entries.toTypedArray()
    val (selectedViewType, onViewTypeSelected) = rememberSaveable {
        mutableStateOf(exhibitViewTypes.find { it == settings.selectedExhibitViewType } ?: exhibitViewTypes[0])
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
                exhibitViewTypes.forEach { viewType ->
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
                            selectedExhibitViewType = selectedViewType
                        )
                    )
                    onDismiss()
                },
                text = stringResource(R.string.save)
            )
        },
    )
}
