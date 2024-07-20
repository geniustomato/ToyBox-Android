package com.clooy.toybox.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberToyBoxAppState(
    navController: NavHostController = rememberNavController()
): ToyBoxAppState {
    // TODO Double check if triggering remember for NavController twice - rememberNavController() + remember(...) - is good
    return remember(
        navController
    ) {
        ToyBoxAppState(navController = navController)
    }
}

class ToyBoxAppState(
    val navController: NavHostController
)
