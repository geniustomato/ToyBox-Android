package com.clooy.toybox.exhibitA

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Stateful Version
@Composable
fun ExhibitAScreen(
    modifier: Modifier = Modifier
) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

//    when (uiState) {
//        DashboardUiState.Loading -> LoadingScreen(modifier = modifier)
//        is DashboardUiState.Success -> DashboardScreen(
//            uiState = uiState,
//            modifier = modifier,
//            viewModel::onEvent
//        )
//    }

    Text(text = "Welcome to Exhibit A")
}

// Stateless Version
//@Composable
//fun ExhibitAScreen(
//    uiState: DashboardUiState,
//    modifier: Modifier = Modifier,
//    onEvent: (DashboardUiEvent) -> Unit,
//) {
//    when (uiState) {
//        DashboardUiState.Loading -> LoadingScreen()
//        is DashboardUiState.Success -> {
//            ExhibitList(
//                data = uiState.data.exhibits,
//                modifier = modifier,
//                onExhibitItemClicked = { exhibit ->
//                    onEvent(DashboardUiEvent.OnExhibitItemClicked(exhibit))
//                }
//            )
//        }
//    }
//}