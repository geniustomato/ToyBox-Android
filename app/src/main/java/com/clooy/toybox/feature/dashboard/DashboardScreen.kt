package com.clooy.toybox.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.toybox.feature.dashboard.exhibit.ui.ExhibitList
import com.clooy.toybox.feature.loading.ui.LoadingScreen

// Stateful Version
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
    onEvent:  (DashboardUiEvent) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
        is DashboardUiState.Success -> DashboardScreen(
            uiState = uiState,
            modifier = modifier,
            onEvent
        )
    }
}

// Stateless Version
@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    modifier: Modifier = Modifier,
    onEvent: (DashboardUiEvent) -> Unit,
) {
    when (uiState) {
        DashboardUiState.Loading -> LoadingScreen()
        is DashboardUiState.Success -> {
            ExhibitList(
                data = uiState.data.exhibits,
                modifier = modifier,
                onExhibitItemClicked = { exhibit ->
                    onEvent(DashboardUiEvent.OnExhibitItemClicked(exhibit))
                }
            )
        }
    }
}
