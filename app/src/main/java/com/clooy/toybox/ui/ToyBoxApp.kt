package com.clooy.toybox.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clooy.toybox.core.designsystem.component.ToyBoxBackground
import com.clooy.toybox.navigation.ToyBoxNavHost

@Composable
internal fun ToyBoxApp(
    modifier: Modifier = Modifier,
    appState: ToyBoxAppState,
) {
    ToyBoxBackground(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        ToyBoxNavHost(appState = appState)
    }
}
