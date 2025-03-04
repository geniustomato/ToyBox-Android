package com.clooy.toybox.feature.dashboard

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
        is DashboardUiState.Success -> DashboardScreen(
            uiState = uiState,
            modifier = modifier,
            onNavigationEvent = onNavigationEvent
        )
    }
}

// Stateless Version
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier,
    onNavigationEvent: (DashboardNavigationEvent) -> Unit,
) {
    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen()
        is DashboardUiState.Success -> {
            var settingsData by rememberSaveable { mutableStateOf(uiState.data.settings) }

            DashboardScaffold(
                settingsData = settingsData,
                onDashboardToolbarEvent = { event ->
                    when (event) {
                        is DashboardToolbarEvent.OnDashboardSettingsUpdate -> {
                            settingsData = event.data
                        }
                    }
                },
                content = { padding ->
                    Column(modifier = modifier.padding(padding)) {
                        when (settingsData.selectedViewType) {
                            ViewType.LIST -> {
                                ExhibitList(
                                    data = uiState.data.exhibits,
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
            )
        }
    }
}

// TODO How to preview different views types?
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
                ),
                settings = DashboardToolbarData.Settings(
                    selectedViewType = ViewType.GALLERY
                )
            )
        ),
        onNavigationEvent = { /* Do Nothing */ },
    )
}
