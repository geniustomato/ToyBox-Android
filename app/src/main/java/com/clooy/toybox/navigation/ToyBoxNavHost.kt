package com.clooy.toybox.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.clooy.toybox.feature.dashboard.DashboardNavigationEvent
import com.clooy.toybox.feature.dashboard.exhibit.data.ExhibitName
import com.clooy.toybox.feature.dashboard.navigation.dashboardScreen
import com.clooy.toybox.feature.dashboard.navigation.navigateToDashboardScreen
import com.clooy.toybox.feature.exhibitA.navigation.exhibitAScreen
import com.clooy.toybox.feature.exhibitA.navigation.navigateToExhibitA
import com.clooy.toybox.feature.onboarding.navigation.ONBOARDING_ROUTE
import com.clooy.toybox.feature.onboarding.navigation.onboardingScreen

@Composable
fun ToyBoxNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController, // TODO Eventually replace this with an AppState encapsulating the navController
    startDestination: String = ONBOARDING_ROUTE
) {
    val context = LocalContext.current

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        val navOptions = navOptions { } // TODO How will I utilize this?

        dashboardScreen(
            onEvent = { event ->
                when (event) {
                    is DashboardNavigationEvent.OnEnterExhibit -> {
                        when (event.exhibit.exhibit) {
                            ExhibitName.ExhibitA -> navController.navigateToExhibitA(navOptions = navOptions)
                            else -> Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        )

        exhibitAScreen()

        onboardingScreen(
            onContinueClicked = {
                navController.navigateToDashboardScreen(navOptions = navOptions)
            }
        )
    }
}
