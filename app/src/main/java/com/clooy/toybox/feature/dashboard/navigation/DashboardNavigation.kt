package com.clooy.toybox.feature.dashboard.navigation

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.clooy.toybox.feature.dashboard.DashboardNavigationEvent
import com.clooy.toybox.feature.dashboard.DashboardRoute
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitId.ExhibitA
import com.clooy.toybox.feature.exhibitA.navigation.navigateToExhibitA

const val DASHBOARD_ROUTE = "dashboard"

fun NavGraphBuilder.dashboardScreen(
    navController: NavHostController,
) {
    composable(DASHBOARD_ROUTE) {
        DashboardRoute(onNavigationEvent = navController::handleDashboardNavigationEvent)
    }
}


fun NavController.navigateToDashboardScreen() =
    navigate(
        route = DASHBOARD_ROUTE,
        navOptions = navOptions {
            // Will always be first in the backstack
            popUpTo(id = graph.startDestinationId) {
                inclusive = true
            }
        }
    )

private fun NavHostController.handleDashboardNavigationEvent(
    event: DashboardNavigationEvent
) {
    when (event) {
        is DashboardNavigationEvent.OnEnterExhibit -> {
            when (event.exhibitId) {
                ExhibitA -> navigateToExhibitA(navOptions = navOptions { })
                else -> Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
