package com.clooy.toybox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.clooy.toybox.ui.theme.ToyBoxTheme
import kotlinx.coroutines.coroutineScope

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ToyBoxApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationRoute.Onboarding.route
) {
//    Surface(
//        modifier = modifier, color = MaterialTheme.colorScheme.background
//    ) {
//        val context = LocalContext.current
//
//        NavHost(
//            modifier = modifier,
//            navController = navController,
//            startDestination = startDestination,
//        ) {
//            composable(NavigationRoute.Dashboard.route) {
//                DashboardScreen(viewModel = DashboardViewModel()) {
//                    when (it) {
//                        is DashboardUiEvent.OnExhibitItemClicked ->
//                            when (it.exhibit.exhibit) {
//                                ExhibitName.ExhibitA -> navController.navigate(NavigationRoute.ExhibitA.route)
//                                else -> Toast.makeText(context, "Not Available", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                    }
//                }
//            }
//
//            composable(NavigationRoute.Onboarding.route) {
//                OnboardingScreen(onContinueClicked = {
//                    navController.navigate(route = NavigationRoute.Dashboard.route) {
//                        popUpTo(route = NavigationRoute.Onboarding.route) {
//                            inclusive = true
//                            saveState = true
//                        }
//                    }
//                })
//            }
//
//            composable(NavigationRoute.ExhibitA.route) {
//                ExhibitAScreen()
//            }
//        }
//    }

    ExhibitViewPagerDemo()
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private   fun ExhibitViewPagerDemo() {
    val pagerState = rememberPagerState(pageCount = { 3 })

    HorizontalPager(
        state = pagerState,
    ) { pageIndex ->
        Box(
            modifier = Modifier
                .padding(20.dp)
                .background(Color.LightGray)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (exhibitPages[pageIndex]) {
                ExhibitPage.ExhibitA -> ExhibitPageSample(pageIndex = pageIndex, state = pagerState)
                ExhibitPage.ExhibitB -> ExhibitPageSample(pageIndex = pageIndex, state = pagerState)
                ExhibitPage.ExhibitC -> ExhibitPageSample(pageIndex = pageIndex, state = pagerState)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExhibitPageSample(modifier: Modifier = Modifier, pageIndex: Int, state :PagerState) {
    Text("Welcome to exhibit $pageIndex")
    if(pageIndex == 2) {
        // TODO How to use coroutine with this...
//        state.animateScrollToPage(0, pageOffsetFraction = 0.45f)
    }
}

sealed class ExhibitPage {
    object ExhibitA : ExhibitPage()
    object ExhibitB : ExhibitPage()
    object ExhibitC : ExhibitPage()
}

val exhibitPages = listOf(ExhibitPage.ExhibitA, ExhibitPage.ExhibitB, ExhibitPage.ExhibitC)

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
