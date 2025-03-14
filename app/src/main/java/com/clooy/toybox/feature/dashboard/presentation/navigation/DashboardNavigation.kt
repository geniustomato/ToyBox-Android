package com.clooy.toybox.feature.dashboard.presentation.navigation

import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.clooy.toybox.feature.dashboard.presentation.dashboard.event.DashboardScreenEvent
import com.clooy.toybox.feature.dashboard.presentation.dashboard.ui.DashboardRoute
import com.clooy.toybox.feature.dashboard.presentation.dashboard.model.ExhibitId.ExhibitA
import com.clooy.toybox.feature.exhibitA.navigation.navigateToExhibitA
import kotlinx.serialization.Serializable

@Serializable
object DashboardRoute

fun NavGraphBuilder.dashboardScreen(
    navController: NavHostController,
) {
    composable<DashboardRoute> {
        DashboardRoute(onNavigationEvent = navController::handleDashboardNavigationEvent)
    }
}


fun NavController.navigateToDashboardScreen() =
    navigate(
        route = DashboardRoute,
        navOptions = navOptions {
            // Will always be first in the backstack
            popUpTo(id = graph.startDestinationId) {
                inclusive = true
            }
        }
    )

private fun NavController.handleDashboardNavigationEvent(
    event: DashboardScreenEvent.Navigation
) {
    when (event) {
        is DashboardScreenEvent.Navigation.OnEnterExhibit -> {
            when (event.exhibitId) {
                ExhibitA -> navigateToExhibitA()
                else -> Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
