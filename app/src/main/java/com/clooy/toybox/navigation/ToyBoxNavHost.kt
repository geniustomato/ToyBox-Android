package com.clooy.toybox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.clooy.toybox.feature.dashboard.navigation.dashboardScreen
import com.clooy.toybox.feature.dashboard.navigation.navigateToDashboardScreen
import com.clooy.toybox.feature.exhibitA.navigation.exhibitAScreen
import com.clooy.toybox.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.clooy.toybox.feature.onboarding.navigation.onboardingScreen
import com.clooy.toybox.ui.ToyBoxAppState

@Composable
fun ToyBoxNavHost(
    modifier: Modifier = Modifier,
    appState: ToyBoxAppState,
    startDestination: String = ONBOARDING_ROUTE
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        val navOptions = navOptions { } // TODO How will I utilize this?

        dashboardScreen(navController = navController)

        exhibitAScreen()

        onboardingScreen(
            onContinueClicked = {
                navController.navigateToDashboardScreen(navOptions = navOptions)
            }
        )
    }
}
