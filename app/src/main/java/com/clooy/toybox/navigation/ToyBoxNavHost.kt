package com.clooy.toybox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.clooy.toybox.feature.dashboard.navigation.dashboardScreen
import com.clooy.toybox.feature.exhibitA.navigation.exhibitAScreen
import com.clooy.toybox.feature.onboarding.navigation.onboardingScreen
import com.clooy.toybox.app.ui.ToyBoxAppState
import kotlin.reflect.KClass

@Composable
fun ToyBoxNavHost(
    modifier: Modifier = Modifier,
    appState: ToyBoxAppState,
    startDestination: KClass<*> = OnboardingRoute::class,
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        onboardingScreen(navController = navController)
        dashboardScreen(navController = navController)
        exhibitAScreen()
    }
}
