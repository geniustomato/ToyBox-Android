package com.clooy.toybox.feature.dashboard.presentation.dashboard.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.toybox.feature.dashboard.presentation.dashboard.event.DashboardScreenEvent
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardToolbar
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.DashboardUiModel
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitId
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitUiModel
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitViewType
import com.clooy.toybox.feature.dashboard.presentation.dashboard.state.DashboardState
import com.clooy.toybox.feature.dashboard.presentation.dashboard.viewmodels.DashboardViewModel
import com.clooy.toybox.feature.loading.ui.LoadingScreen

// Stateful Version
@Composable
internal fun DashboardRoute(
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = hiltViewModel(),
    onNavigationEvent: (DashboardScreenEvent.Navigation) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        DashboardState.Loading -> LoadingScreen(modifier = modifier)
        is DashboardState.Success -> DashboardScreen(
            uiState = uiState,
            modifier = modifier,
            onNavigationEvent = onNavigationEvent
        )
    }
}

// Stateless Version
@Composable
private fun DashboardScreen(
    uiState: DashboardState,
    modifier: Modifier = Modifier,
    onNavigationEvent: (DashboardScreenEvent.Navigation) -> Unit,
) {
    when (uiState) {
        DashboardState.Loading -> LoadingScreen()
        is DashboardState.Success -> {
            var settingsData by rememberSaveable { mutableStateOf(uiState.data.settings) }

            DashboardScaffold(
                settingsData = settingsData,
                onDashboardToolbarEvent = { event ->
                    when (event) {
                        is DashboardScreenEvent.Toolbar.OnSettingsUpdate -> {
                            settingsData = event.data
                        }
                    }
                },
                content = { padding ->
                    Column(modifier = modifier.padding(padding)) {
                        when (settingsData.selectedExhibitViewType) {
                            ExhibitViewType.LIST -> {
                                ExhibitList(
                                    data = uiState.data.exhibits,
                                    onEnterExhibitClicked = { id ->
                                        onNavigationEvent(
                                            DashboardScreenEvent.Navigation.OnEnterExhibit(
                                                exhibitId = id
                                            )
                                        )
                                    },
                                )
                            }

                            ExhibitViewType.GALLERY -> {
                                Toast.makeText(
                                    LocalContext.current,
                                    "Gallery is not available",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            )
        }
    }
}

// TODO How to preview different views types?
@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        uiState = DashboardState.Success(
            data = DashboardUiModel(
                exhibits = listOf(
                    ExhibitUiModel(
                        id = ExhibitId.ExhibitA,
                        name = "Exhibit Name",
                        description = "Description",
                        isActive = true,
                    )
                ),
                settings = DashboardToolbar.Settings(selectedExhibitViewType = ExhibitViewType.LIST)
            )
        ),
        onNavigationEvent = { /* Do Nothing */ },
    )
}
