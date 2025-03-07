package com.clooy.toybox.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.clooy.toybox.core.designsystem.theme.ToyBoxTheme
import com.clooy.toybox.app.ui.ToyBoxApp
import com.clooy.toybox.app.ui.rememberToyBoxAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val appState = rememberToyBoxAppState()

            ToyBoxTheme {
                ToyBoxApp(
                    modifier = Modifier.fillMaxSize(),
                    appState = appState,
                )
            }
        }
    }
}
