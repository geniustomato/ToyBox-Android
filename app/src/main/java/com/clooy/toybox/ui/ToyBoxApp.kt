package com.clooy.toybox.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clooy.toybox.navigation.ToyBoxNavHost

@Composable
internal fun ToyBoxApp(
    modifier: Modifier = Modifier,
    appState: ToyBoxAppState,
) {
    // TODO Why again do I have a Surface wrapper here?
    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        ToyBoxNavHost(appState = appState)
    }
}
