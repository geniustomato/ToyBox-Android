package com.clooy.toybox.feature.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.onboarding.ui.OnboardingScreen

const val ONBOARDING_ROUTE = "onboarding"

fun NavGraphBuilder.onboardingScreen(onContinueClicked: () -> Unit) {
    composable(ONBOARDING_ROUTE) {
        OnboardingScreen(onContinueClicked = onContinueClicked)
    }
}

fun NavController.navigateToOnboardingScreen(navOptions: NavOptions) {
    navigate(route = ONBOARDING_ROUTE, navOptions = navOptions)
}
