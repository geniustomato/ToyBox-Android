package com.clooy.toybox.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.dashboard.presentation.navigation.navigateToDashboardScreen
import com.clooy.toybox.feature.onboarding.ui.OnboardingScreen
import kotlinx.serialization.Serializable

@Serializable
object OnboardingRoute

fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable<OnboardingRoute> {
        OnboardingScreen(onContinueClicked = navController::navigateToDashboardScreen)
    }
}
