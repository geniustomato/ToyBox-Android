package com.clooy.toybox.feature.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.dashboard.DashboardNavigationEvent
import com.clooy.toybox.feature.dashboard.DashboardRoute

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboardScreen(onEvent: (DashboardNavigationEvent) -> Unit) {
    composable(DASHBOARD_ROUTE) {
        DashboardRoute(onEvent = onEvent)
    }
}

fun NavController.navigateToDashboardScreen(navOptions: NavOptions) =
    navigate(route = DASHBOARD_ROUTE, navOptions = navOptions)
