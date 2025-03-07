package com.clooy.toybox.feature.dashboard.presentation.dashboard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clooy.toybox.feature.dashboard.domain.usecase.GetDashboardUseCase
import com.clooy.toybox.feature.dashboard.presentation.dashboard.mapper.toDashboardUiModel
import com.clooy.toybox.feature.dashboard.presentation.dashboard.state.DashboardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
internal class DashboardViewModel @Inject constructor(
    private val getDashboardUseCase: GetDashboardUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DashboardState>(DashboardState.Loading)

    // TODO Deep dive on coroutines and see how my implementation fits with how it actually works...
    val uiState: StateFlow<DashboardState> =
        _uiState.stateIn(
            scope = viewModelScope,
            initialValue = DashboardState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )

    init {
        runBlocking {
            val data = getDashboardUseCase()
            _uiState.value = DashboardState.Success(
                data = data.toDashboardUiModel()
            )
        }
    }
}
