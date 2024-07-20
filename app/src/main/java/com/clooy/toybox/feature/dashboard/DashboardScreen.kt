package com.clooy.toybox.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            ExhibitList(
                data = uiState.data.exhibits,
                modifier = modifier,
                onEnterExhibitClicked = { id ->
                    onNavigationEvent(DashboardNavigationEvent.OnEnterExhibit(exhibitId = id))
                },
            )
        }
    }
}
