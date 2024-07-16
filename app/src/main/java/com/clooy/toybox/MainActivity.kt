package com.clooy.toybox

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.clooy.toybox.dashboard.DashboardScreen
import com.clooy.toybox.dashboard.DashboardUiEvent
import com.clooy.toybox.dashboard.DashboardViewModel
import com.clooy.toybox.dashboard.exhibit.data.ExhibitName
import com.clooy.toybox.exhibitA.ExhibitAScreen
import com.clooy.toybox.onboarding.ui.OnboardingScreen
import com.clooy.toybox.core.designsystem.theme.ToyBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToyBoxTheme {
                ToyBoxApp(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}

@Composable
private fun ToyBoxApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRoute.Onboarding.route
) {
    Surface(
        modifier = modifier, color = MaterialTheme.colorScheme.background
    ) {
        val context = LocalContext.current

        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(NavigationRoute.Dashboard.route) {
                DashboardScreen(viewModel = DashboardViewModel()) {
                    when (it) {
                        is DashboardUiEvent.OnExhibitItemClicked ->
                            when (it.exhibit.exhibit) {
                                ExhibitName.ExhibitA -> navController.navigate(NavigationRoute.ExhibitA.route)
                                else -> Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT)
                                    .show()
                            }
                    }
                }
            }

            composable(NavigationRoute.Onboarding.route) {
                OnboardingScreen(onContinueClicked = {
                    navController.navigate(route = NavigationRoute.Dashboard.route) {
                        popUpTo(route = NavigationRoute.Onboarding.route) {
                            inclusive = true
                            saveState = true
                        }
                    }
                })
            }

            composable(NavigationRoute.ExhibitA.route) {
                ExhibitAScreen()
            }
        }
    }
}


// TODO Trial navigation routes
sealed class NavigationRoute(val route: String) {
    object Onboarding : NavigationRoute("onboarding")
    object Dashboard : NavigationRoute("dashboard")
    object ExhibitA : NavigationRoute("exhibitA")
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun ToyBoxAppPreview() {
    ToyBoxTheme {
        ToyBoxApp(modifier = Modifier.fillMaxSize())
    }
}
