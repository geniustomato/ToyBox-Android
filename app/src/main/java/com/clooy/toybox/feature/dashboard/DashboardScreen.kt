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
    // TODO Also a stateful version? Only should exist??
    modifier: Modifier = Modifier,
    viewModel: DashboardViewModel = DashboardViewModel(), // TODO Figure out how to use hiltViewModel() here...
    onEvent: (DashboardNavigationEvent) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
        is DashboardUiState.Success -> DashboardScreen(
            uiState = uiState,
            modifier = modifier,
            onEvent = onEvent
        )
    }
}

// Stateless Version
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier,
    onEvent: (DashboardNavigationEvent) -> Unit,
) {
    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen()
        is DashboardUiState.Success -> {
            ExhibitList(
                data = uiState.data.exhibits,
                modifier = modifier,
                onExhibitItemClicked = { exhibit ->
                    onEvent(DashboardNavigationEvent.OnEnterExhibit(exhibit))
                }
            )
        }
    }
}
