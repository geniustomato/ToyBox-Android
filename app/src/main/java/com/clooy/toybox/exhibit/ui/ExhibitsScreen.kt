package com.clooy.toybox.exhibit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.clooy.toybox.loading.ui.LoadingScreen

// Stateful Version
@Composable
fun ExhibitsScreen(
    viewModel: ExhibitViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState) {
        ExhibitsUiState.Loading -> LoadingScreen(modifier = modifier)
        is ExhibitsUiState.Success -> ExhibitsScreen(uiState = uiState, modifier = modifier)
    }
}

// Stateless Version
@Composable
fun ExhibitsScreen(
    uiState: ExhibitsUiState,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        ExhibitsUiState.Loading -> LoadingScreen()
        is ExhibitsUiState.Success -> {
            ExhibitsList(data = uiState.exhibits, modifier = modifier)
        }
    }
}
