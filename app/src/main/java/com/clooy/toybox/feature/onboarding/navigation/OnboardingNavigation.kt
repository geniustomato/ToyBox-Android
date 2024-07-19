package com.clooy.toybox.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.dashboard.navigation.navigateToDashboardScreen
import com.clooy.toybox.feature.onboarding.ui.OnboardingScreen

const val ONBOARDING_ROUTE = "onboarding"

fun NavGraphBuilder.onboardingScreen(navController: NavHostController) {
    composable(ONBOARDING_ROUTE) {
        OnboardingScreen(onContinueClicked = navController::navigateToDashboardScreen)
    }
}

fun NavController.navigateToOnboardingScreen(navOptions: NavOptions) {
    navigate(route = ONBOARDING_ROUTE, navOptions = navOptions)
}
