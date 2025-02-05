package com.clooy.toybox.feature.dashboard

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.toybox.R
import com.clooy.toybox.core.designsystem.component.icon.ToyBoxIcons
import com.clooy.toybox.feature.dashboard.exhibit.data.Exhibit
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId
import com.clooy.toybox.feature.dashboard.exhibit.ui.ExhibitList
import com.clooy.toybox.feature.loading.ui.LoadingScreen

// Stateful Version
@Composable
internal fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = DashboardViewModel(), // TODO Figure out how to use hiltViewModel() here...
    onNavigationEvent: (DashboardNavigationEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var settingsData by rememberSaveable  {  mutableStateOf(SettingsData(selectedViewType = ViewType.LIST, showSettingsDialog = false)) }

    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
        is DashboardUiState.Success -> DashboardScreen(
            uiState = uiState,
            modifier = modifier,
            onSettingsClicked = { settingsData = settingsData.copy(showSettingsDialog = it) },
            settingsViewType = settingsData.selectedViewType,
            onNavigationEvent = onNavigationEvent
        )
    }

    if (settingsData.showSettingsDialog) {
        SettingsDialog(
            selectedViewType = settingsData.selectedViewType,
            onSaveSettings = {
                settingsData = settingsData.copy(selectedViewType = it)
            },
            onDismiss = {
                settingsData = settingsData.copy(showSettingsDialog = false)
            }
        )
    }
}

// Stateless Version
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier,
    onSettingsClicked: (Boolean) -> Unit,
    settingsViewType: ViewType,
    onNavigationEvent: (DashboardNavigationEvent) -> Unit,
) {

    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen()
        is DashboardUiState.Success -> {
            DashboardScaffold(
                onSettingsClicked = {
                    onSettingsClicked(true)
                }
            ) { padding ->
                Column(modifier = modifier.padding(padding)) {
                    when (settingsViewType) {
                        ViewType.LIST -> {
                            ExhibitList(
                                data = uiState.data.exhibits, // TODO Maybe passing down the "view as" type here
                                onEnterExhibitClicked = { id ->
                                    onNavigationEvent(
                                        DashboardNavigationEvent.OnEnterExhibit(
                                            exhibitId = id
                                        )
                                    )
                                },
                            )
                        }

                        ViewType.GALLERY -> {
                            Toast.makeText(
                                LocalContext.current,
                                "Gallery is not available",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}

enum class ViewType(@StringRes val label: Int) {
    LIST(R.string.list),
    GALLERY(R.string.gallery)
}

@Composable
fun SettingsDialog(
    modifier: Modifier = Modifier,
    selectedViewType: ViewType,
    onSaveSettings: (ViewType) -> Unit,
    onDismiss: () -> Unit,
) {
    val options = ViewType.entries.toTypedArray()
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(options.find { it == selectedViewType } ?: options[0])
    }

    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        text = {
            Column {
                Text(text = "View as...", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                options.forEach { option ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            option == selectedOption,
                            onClick = {
                                onOptionSelected(option)
                            })
                        Text(text = stringResource(id = option.label))
                    }
                }
            }
        },
        confirmButton = {
            Text(
                modifier = Modifier.clickable {
                    onSaveSettings(selectedOption)
                    onDismiss()
                },
                text = "Save"
            )
        },
    )
}

@Composable
private fun DashboardScaffold(
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = { DashboardToolbar(onSettingsClicked = onSettingsClicked) },
        content = content,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardToolbar(
    modifier: Modifier = Modifier,
    onSettingsClicked: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { /* No Title */ },
        actions = {
            IconButton(onClick = onSettingsClicked) {
                Icon(
                    imageVector = ToyBoxIcons.Settings,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    )
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        uiState = DashboardUiState.Success(
            data = DashboardUiData(
                exhibits = listOf(
                    Exhibit(
                        id = ExhibitId.ExhibitA,
                        name = "Exhibit Name",
                        description = "Description",
                        isActive = true,
                    )
                )
            )
        ),
        onSettingsClicked = { /* Do Nothing */ },
        settingsViewType = ViewType.LIST,
        onNavigationEvent = { /* Do Nothing */ }
    )
}
