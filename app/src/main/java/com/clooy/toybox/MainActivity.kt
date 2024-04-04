package com.clooy.toybox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.clooy.toybox.dashboard.DashboardViewModel
import com.clooy.toybox.dashboard.DashboardScreen
import com.clooy.toybox.onboarding.ui.OnboardingScreen
import com.clooy.toybox.ui.theme.ToyBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToyBoxTheme {
                ToyBoxApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun ToyBoxApp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = {
                shouldShowOnboarding = false
            })
        } else {
            DashboardScreen(viewModel = DashboardViewModel())
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun ToyBoxAppPreview() {
    ToyBoxTheme {
        ToyBoxApp(modifier = Modifier.fillMaxSize())
    }
}
