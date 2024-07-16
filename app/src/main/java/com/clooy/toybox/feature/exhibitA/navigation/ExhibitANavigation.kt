package com.clooy.toybox.feature.exhibitA.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.clooy.toybox.feature.exhibitA.ExhibitAScreen

const val EXHIBIT_A_ROUTE = "exhibitA"

fun NavGraphBuilder.exhibitAScreen() {
    composable(EXHIBIT_A_ROUTE) {
        ExhibitAScreen()
    }
}

fun NavController.navigateToExhibitA(navOptions: NavOptions) {
    navigate(route = EXHIBIT_A_ROUTE, navOptions = navOptions)
}