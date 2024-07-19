package com.clooy.toybox.feature.exhibitA.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.exhibitA.ExhibitAScreen
import kotlinx.serialization.Serializable

@Serializable
object ExhibitARoute

fun NavGraphBuilder.exhibitAScreen() {
    composable<ExhibitARoute> {
        ExhibitAScreen()
    }
}

fun NavController.navigateToExhibitA(navOptions: NavOptions) {
    navigate(route = ExhibitARoute, navOptions = navOptions)
}